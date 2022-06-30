package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.SocieteStatistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocieteStatistiqueRepository extends JpaRepository<SocieteStatistique, Long> {
    List<SocieteStatistique> findBySocieteId(Long idSociete);
}
