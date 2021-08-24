package com.socialnetworkmonitoring.service.impl;

import com.socialnetworkmonitoring.models.Utilisateur;
import com.socialnetworkmonitoring.repository.UtilisateurRepository;
import com.socialnetworkmonitoring.service.dto.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("Utilisateur non trouvé : "+login)
        );
        return UserDetailsImpl.build(utilisateur);
    }
}
