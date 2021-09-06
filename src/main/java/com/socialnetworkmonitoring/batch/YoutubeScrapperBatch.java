package com.socialnetworkmonitoring.batch;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.repository.ProfilRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class YoutubeScrapperBatch {

    private ProfilRepository profilRepository;

    private final Logger log = LoggerFactory.getLogger(YoutubeScrapperBatch.class);

    @Autowired
    public YoutubeScrapperBatch(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    /*
    @Scheduled(cron = "* * * * * *")
    public void getYoutubeStats() {
        this.profilRepository.findAll().forEach(
                profil -> {
                    Long viewsCount = this.getYoutubeViews(profil);
                    System.out.println(viewsCount);
                }
        );
    }*/

    private Long getYoutubeViews(Profil profil) {
        Long viewsCount = null;
        try {
            if (!StringUtils.isBlank(profil.getLienYoutube())) {
                WebClient client = new WebClient(BrowserVersion.CHROME);
                client.getOptions().setCssEnabled(false);
                client.getOptions().setJavaScriptEnabled(false);
                HtmlPage page = client.getPage(profil.getLienYoutube());
                String viewsText = page.asXml().substring(page.asXml().indexOf("subscriberCountText"), page.asXml().indexOf("subscribers"));
                viewsText = viewsText.substring(viewsText.lastIndexOf("\"") + 1);
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération des données du site Youtube pour le compte " + profil.getNom() + " : " + e.getMessage());
        }
        return viewsCount;
    }

}
