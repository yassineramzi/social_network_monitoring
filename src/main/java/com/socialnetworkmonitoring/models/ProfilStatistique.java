package com.socialnetworkmonitoring.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@DynamicUpdate
@Getter
@Setter
@Table(name = "profil_statistique")
public class ProfilStatistique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_statistique")
    private Date dateStatistique;

    @Column(name="nombre_followers_youtube")
    private BigInteger nombreFollowersYoutube;

    @Column(name="nombre_vues_youtube")
    private BigInteger nombreVuesYoutube;

    @Column(name="nombre_followers_facebook")
    private BigInteger nombreFollowersFacebook;

    @Column(name="nombre_followers_twitter")
    private BigInteger nombreFollowersTwitter;

    @Column(name="nombre_followers_instagram")
    private BigInteger nombreFollowersInstagram;

    @ManyToOne
    @JoinColumn(name="id_profil")
    private Profil profil;
}
