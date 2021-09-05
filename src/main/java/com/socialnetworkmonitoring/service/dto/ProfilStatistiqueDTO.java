package com.socialnetworkmonitoring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilStatistiqueDTO implements Serializable {

    private Long id;

    private Date dateStatistique;

    private Long nombreFollowersYoutube;

    private Long nombreVuesYoutube;

    private Long nombreFollowersFacebook;

    private Long nombreFollowersTwitter;

    private Long nombreFollowersInstagram;

    private Long profilId;
}
