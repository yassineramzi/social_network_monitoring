package com.socialnetworkmonitoring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilDTO implements Serializable {

    private Long id;

    private String nom;

    private String lienYoutube;

    private String lienTwitter;

    private String lienFacebook;

    private String lienInstagram;

    private Long dossierId;
}
