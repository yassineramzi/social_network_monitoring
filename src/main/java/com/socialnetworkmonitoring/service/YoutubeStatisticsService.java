package com.socialnetworkmonitoring.service;

import com.google.api.services.youtube.model.ChannelStatistics;
import com.socialnetworkmonitoring.models.Profil;

import java.io.IOException;

public interface YoutubeStatisticsService {
    ChannelStatistics getYoutubeStatisticsByProfil(Profil profil) throws IOException;
}
