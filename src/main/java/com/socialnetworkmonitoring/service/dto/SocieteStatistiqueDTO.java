package com.socialnetworkmonitoring.service.dto;

import com.socialnetworkmonitoring.models.Societe;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocieteStatistiqueDTO implements Serializable {
    private Long id;

    private Date dateStatistique;

    private Float prixGazoil;

    private Float prixEssence;

    private Long societeId;
}
