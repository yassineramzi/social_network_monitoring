package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.DossierSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierSocialRepository extends JpaRepository<DossierSocial, Long> {
    Boolean existsByNom(String nom);

    Boolean existsByNomAndIdIsNot(String nom, Long id);

    @Query(value = "select distinct d from DossierSocial d "+
                    "LEFT JOIN FETCH d.profils")
    List<DossierSocial> findAll();

    @Query(value = "select distinct d from DossierSocial d "+
                    "LEFT JOIN FETCH d.profils where d.id = :id")
    Optional<DossierSocial> findById(@Param("id")Long id);
}
