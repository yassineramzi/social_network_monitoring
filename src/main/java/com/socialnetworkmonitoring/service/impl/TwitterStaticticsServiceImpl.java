package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.Profil;
import com.socialnetworkmonitoring.service.TwitterStaticticsService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class TwitterStaticticsServiceImpl implements TwitterStaticticsService {

    @Value("${twitter.bearer}")
    private String bearerToken;

    @Override
    public BigInteger getTwitterStatisticsByProfil(Profil profil) throws URISyntaxException, IOException {
        BigInteger nombreFollowers = null;
        String searchResponse = null;

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/users/by/username/"+profil.getLienTwitter());
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("user.fields", "public_metrics"));
        uriBuilder.addParameters(queryParameters);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", this.bearerToken));
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
        nombreFollowers = ((JSONObject) ((JSONObject) jsonObject.get("data")).get("public_metrics")).getBigInteger("followers_count");
        return nombreFollowers;
    }
}

