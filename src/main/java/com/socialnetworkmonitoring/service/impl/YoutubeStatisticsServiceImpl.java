package com.socialnetworkmonitoring.service.impl;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ChannelStatistics;
import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.service.YoutubeStatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class YoutubeStatisticsServiceImpl implements YoutubeStatisticsService {

    @Value("${youtube.api.secret}")
    private String apiSecret;

    @Value("${youtube.applicationName}")
    private String applicationName;

    public ChannelStatistics getYoutubeStatisticsByProfil(Profil profil) throws IOException {
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
        return channels.get(0) != null ? channels.get(0).getStatistics() : null;
    }
}
