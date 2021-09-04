package com.socialnetworkmonitoring.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@DynamicUpdate
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

    @ManyToOne
    @JoinColumn(name="id_dossier_social")
    private DossierSocial dossierSocial;

}
