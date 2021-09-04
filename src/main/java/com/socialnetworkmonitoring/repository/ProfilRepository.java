package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {
    Boolean existsByNom(String nom);

    Boolean existsByNomAndIdIsNot(String nom, Long id);

    List<Profil> findByDossierId(Long idDossier);
}
