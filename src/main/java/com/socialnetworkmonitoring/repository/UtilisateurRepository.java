package com.socialnetworkmonitoring.repository;

import com.socialnetworkmonitoring.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the {@link Utilisateur } entity.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query(value="select distinct u from Utilisateur u " +
            "LEFT JOIN FETCH u.roles "+
            "where u.login = :login")
    Optional<Utilisateur> findByLogin(@Param("login") String login);
}
