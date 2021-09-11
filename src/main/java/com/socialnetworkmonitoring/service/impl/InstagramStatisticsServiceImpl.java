package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.service.InstagramStatisticsService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;

@Service
public class InstagramStatisticsServiceImpl implements InstagramStatisticsService {

    @Override
    public BigInteger getInstagramStatisticsByProfil(Profil profil) throws URISyntaxException, IOException {
        BigInteger nombreFollowers = null;
        String searchResponse = null;

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder("");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            searchResponse = EntityUtils.toString(entity, "UTF-8");
            nombreFollowers = this.getNombreFollowersFromTwitterResponse(searchResponse);
        }
        return nombreFollowers;
    }

    private BigInteger getNombreFollowersFromTwitterResponse(String searchResponse) {
        BigInteger nombreFollowers = null;
        JSONObject jsonObject = new JSONObject(searchResponse);
        System.out.println(searchResponse);
        System.out.println(jsonObject.get("graphql"));
        nombreFollowers = ((JSONObject) ((JSONObject) jsonObject.get("graphql")).get("edge_followed_by")).getBigInteger("count");
        return nombreFollowers;
    }
}
