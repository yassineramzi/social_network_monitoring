package com.socialnetworkmonitoring.batch;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.models.ProfilStatistique;
import com.socialnetworkmonitoring.repository.ProfilRepository;
import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class YoutubeApiBatch {
    @Value("${youtube.api.secret}")
    private String apiSecret;

    @Value("${youtube.applicationName}")
    private String applicationName;

    private ProfilRepository profilRepository;

    private ProfilStatistiqueRepository profilStatistiqueRepository;

    private final Logger log = LoggerFactory.getLogger(YoutubeApiBatch.class);

    @Autowired
    public YoutubeApiBatch(ProfilRepository profilRepository, ProfilStatistiqueRepository profilStatistiqueRepository) {
        this.profilRepository = profilRepository;
        this.profilStatistiqueRepository = profilStatistiqueRepository;
    }

    @Scheduled(cron = "0 00 18 * * *")
    public void getYoutubeStats() {
        this.profilRepository.findAll().parallelStream().forEach(
                profil -> {
                    try {
                        if (!StringUtils.isBlank(profil.getLienYoutube())) {
                            ChannelStatistics youtubeStatistics = this.getYoutubeStatistics(profil);
                            ProfilStatistique profilStatistique = new ProfilStatistique();
                            profilStatistique.setDateStatistique(new Date());
                            profilStatistique.setNombreFollowersYoutube(youtubeStatistics.getSubscriberCount());
                            profilStatistique.setNombreVuesYoutube(youtubeStatistics.getViewCount());
                            profilStatistique.setProfil(profil);
                            this.profilStatistiqueRepository.save(profilStatistique);
                        }
                    } catch(IOException ioException) {
                        log.error("Une erreur est survenue lors de la récupération des données du site Youtube pour le compte " + profil.getNom() + " : " + ioException.getMessage());
                    }
                }
        );
    }

    private ChannelStatistics getYoutubeStatistics(Profil profil) throws IOException {
        HttpRequestInitializer httpRequestInitializer = new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        };
        YouTube youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), httpRequestInitializer)
                .setApplicationName(this.applicationName)
                .build();
        YouTube.Channels.List request = youTube.channels().list("statistics");
        request.setId(profil.getLienYoutube());
        request.setKey(this.apiSecret);
        ChannelListResponse response = request.execute();
        List<Channel> channels = response.getItems();
        return channels.get(0).getStatistics();
    }
}

