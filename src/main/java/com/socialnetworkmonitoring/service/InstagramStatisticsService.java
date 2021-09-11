package com.socialnetworkmonitoring.service;

import com.socialnetworkmonitoring.models.Profil;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;

public interface InstagramStatisticsService {
    BigInteger getInstagramStatisticsByProfil(Profil profil) throws URISyntaxException, IOException;
}

