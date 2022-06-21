package com.socialnetworkmonitoring.models;

import com.socialnetworkmonitoring.enums.ECategorie;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dossier_social")
public class DossierSocial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom", unique = true)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name="categorie", length = 100)
    private ECategorie categorie;

    @OneToMany(mappedBy="dossierSocial", cascade=CascadeType.ALL)
    private List<Profil> profils = new ArrayList<>();
}
