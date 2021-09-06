package com.socialnetworkmonitoring.batch;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;

import java.io.IOException;
import java.util.List;

public class YoutubeApiBatch {

    public static void main(String[] args) throws IOException {
        HttpRequestInitializer httpRequestInitializer = new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        };
        YouTube youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), httpRequestInitializer)
                .setApplicationName("Social-youtube-data")
                .build();
        YouTube.Channels.List request = youTube.channels().list("statistics");
        request.setId("UC8mYO43YpE3oUSMQzZcsFUQ");
        request.setKey("AIzaSyA4dChZXe0nQcuYHfi5-BWzQyyso16u28o");
        ChannelListResponse response = request.execute();

        List<Channel> channels = response.getItems();
        for (Channel channel : channels) {
            System.out.println(channel.getStatistics());
        }
    }
}

