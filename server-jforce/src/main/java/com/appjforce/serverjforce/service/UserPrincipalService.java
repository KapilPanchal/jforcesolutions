package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.exceptions.CustomUserException;
import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Inside loadUserByUsername() method of UserPrincipalService");
        final AppUser appUser = userRepo.getByUsername(username).get();
        if (appUser == null){
            throw new CustomUserException(String.format("Username %s not found", username));
        }

        UserDetails userDetails = User.withUsername(appUser.getUsername())
                                                    .password(appUser.getPassword())
                                                    .roles(appUser.getRole().toString())
                                                    .authorities(appUser.getRole().toString())
                                                    .build();
        return userDetails;
    }
}
