package com.socialnetworkmonitoring.models;

import com.socialnetworkmonitoring.enums.ECategorie;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "dossier_social")
public class DossierSocial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name="categorie", length = 100)
    private ECategorie categorie;
}
