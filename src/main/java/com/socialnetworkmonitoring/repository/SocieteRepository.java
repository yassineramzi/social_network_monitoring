package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {
    Boolean existsByNomIgnoreCase(String nom);
}
