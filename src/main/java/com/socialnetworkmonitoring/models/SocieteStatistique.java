package com.socialnetworkmonitoring.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@DynamicUpdate
@Getter
@Setter
@Table(name = "societe_statistique")
public class SocieteStatistique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_statistique")
    private Date dateStatistique;

    @Column(name="prix_gazoil")
    private Float prixGazoil;

    @Column(name="prix_essence")
    private Float prixEssence;

    @ManyToOne
    @JoinColumn(name="id_societe")
    private Societe societe;
}
