package com.socialnetworkmonitoring.batch;

import com.google.api.services.youtube.model.ChannelStatistics;
import com.socialnetworkmonitoring.models.ProfilStatistique;
import com.socialnetworkmonitoring.repository.ProfilRepository;
import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import com.socialnetworkmonitoring.service.TwitterStaticticsService;
import com.socialnetworkmonitoring.service.YoutubeStatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

@Component
public class StatisticsBatch {

    private YoutubeStatisticsService youtubeStatisticsService;

    private TwitterStaticticsService twitterStaticticsService;

    private ProfilRepository profilRepository;

    private ProfilStatistiqueRepository profilStatistiqueRepository;

    private final Logger log = LoggerFactory.getLogger(StatisticsBatch.class);

    @Autowired
    public StatisticsBatch(ProfilRepository profilRepository, ProfilStatistiqueRepository profilStatistiqueRepository, YoutubeStatisticsService youtubeStatisticsService, TwitterStaticticsService twitterStaticticsService) {
        this.profilRepository = profilRepository;
        this.profilStatistiqueRepository = profilStatistiqueRepository;
        this.youtubeStatisticsService = youtubeStatisticsService;
        this.twitterStaticticsService = twitterStaticticsService;
    }

    @Scheduled(cron = "00 00 18 * * *")
    public void getProfilStatistics() {
        this.profilRepository.findAll().parallelStream().forEach(
                profil -> {
                    try {
                        ProfilStatistique profilStatistique = new ProfilStatistique();
                        profilStatistique.setDateStatistique(new Date());
                        profilStatistique.setProfil(profil);
                        if (!StringUtils.isBlank(profil.getLienYoutube())) {
                            ChannelStatistics youtubeStatistics = this.youtubeStatisticsService.getYoutubeStatisticsByProfil(profil);
                            profilStatistique.setNombreFollowersYoutube(youtubeStatistics != null ? youtubeStatistics.getSubscriberCount() : null);
                            profilStatistique.setNombreVuesYoutube(youtubeStatistics != null ? youtubeStatistics.getViewCount() : null);
                        }
                        if (!StringUtils.isBlank(profil.getLienTwitter())) {
                            profilStatistique.setNombreFollowersTwitter(this.twitterStaticticsService.getTwitterStatisticsByProfil(profil));
                        }
                        this.profilStatistiqueRepository.save(profilStatistique);
                    } catch(IOException | URISyntaxException exception) {
                        log.error("Une erreur est survenue lors de la récupération des données pour le compte " + profil.getNom() + " : " + exception.getMessage());
                    }
                }
        );
    }

}

