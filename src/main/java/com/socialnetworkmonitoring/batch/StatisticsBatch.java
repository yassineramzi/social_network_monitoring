package com.socialnetworkmonitoring.batch;

import com.google.api.services.youtube.model.ChannelStatistics;
import com.socialnetworkmonitoring.models.ProfilStatistique;
import com.socialnetworkmonitoring.repository.ProfilRepository;
import com.socialnetworkmonitoring.repository.ProfilStatistiqueRepository;
import com.socialnetworkmonitoring.service.InstagramStatisticsService;
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

    private ProfilRepository profilRepository;

    private ProfilStatistiqueRepository profilStatistiqueRepository;

    private YoutubeStatisticsService youtubeStatisticsService;

    private TwitterStaticticsService twitterStaticticsService;

    private InstagramStatisticsService instagramStatisticsService;

    private final Logger log = LoggerFactory.getLogger(StatisticsBatch.class);

    @Autowired
    public StatisticsBatch(ProfilRepository profilRepository, ProfilStatistiqueRepository profilStatistiqueRepository, YoutubeStatisticsService youtubeStatisticsService, TwitterStaticticsService twitterStaticticsService, InstagramStatisticsService instagramStatisticsService) {
        this.profilRepository = profilRepository;
        this.profilStatistiqueRepository = profilStatistiqueRepository;
        this.youtubeStatisticsService = youtubeStatisticsService;
        this.twitterStaticticsService = twitterStaticticsService;
        this.instagramStatisticsService = instagramStatisticsService;
    }

    @Scheduled(cron = "0 */2 * ? * *")
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
                        // https://stackoverflow.com/questions/63709996/how-to-get-instagram-follower-count-from-instagram-public-account-after-2020-ins
                        // https://www.edureka.co/community/358/how-to-execute-a-python-file-with-few-arguments-in-java#:~:text=You%20can%20use%20Java%20Runtime,and%20then%20set%20it%20executable.&text=Hope%20this%20helps%20and%20if,learn%20about%20Java%20in%20detail.
                        /*
                        if (!StringUtils.isBlank(profil.getLienInstagram())) {
                            profilStatistique.setNombreFollowersInstagram(this.instagramStatisticsService.getInstagramStatisticsByProfil(profil));
                        }*/
                        this.profilStatistiqueRepository.save(profilStatistique);
                    } catch(Exception exception) {
                        log.error("Une erreur est survenue lors de la récupération des données pour le compte " + profil.getNom() + " : " + exception.getMessage());
                    }
                }
        );
    }

}

