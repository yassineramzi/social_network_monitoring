package com.socialnetworkmonitoring.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@DynamicUpdate
@Table(name = "profil_statistique")
public class ProfilStatistique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_statistique")
    private Date dateStatistique;

    @Column(name="nombre_followers_youtube")
    private Long nombreFollowersYoutube;

    @Column(name="nombre_vues_youtube")
    private Long nombreVuesYoutube;

    @Column(name="nombre_followers_facebook")
    private Long nombreFollowersFacebook;

    @Column(name="nombre_followers_twitter")
    private Long nombreFollowersTwitter;

    @Column(name="nombre_followers_instagram")
    private Long nombreFollowersInstagram;

    @ManyToOne
    @JoinColumn(name="id_profil")
    private Profil profil;
}
