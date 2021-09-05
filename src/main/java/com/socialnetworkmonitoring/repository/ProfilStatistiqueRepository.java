package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.ProfilStatistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilStatistiqueRepository  extends JpaRepository<ProfilStatistique, Long> {
    List<ProfilStatistique> findByProfilId(Long idProfil);
}
