package com.socialnetworkmonitoring.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "profil")
public class Profil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;

    @Column(name="lien_youtube")
    private String lienYoutube;

    @Column(name="lien_twitter")
    private String lienTwitter;

    @Column(name="lien_facebook")
    private String lienFacebook;

    @Column(name="lien_instagram")
    private String lienInstagram;

    @Column(name="id_dossier_social")
    private Long idDossierSocial;

}
