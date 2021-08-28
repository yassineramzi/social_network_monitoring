package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.DossierSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierSocialRepository extends JpaRepository<DossierSocial, Long> {
    Boolean existsByNom(String nom);

    Boolean existsByNomAndIdIsNot(String nom, Long id);
}
