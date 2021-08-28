package com.socialnetworkmonitoring.service.dto;

import com.socialnetworkmonitoring.enums.ECategorie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierSocialDTO implements Serializable {

    private Long id;

    private String nom;

    private ECategorie categorie;

    private List<ProfilDTO> profils;
}
