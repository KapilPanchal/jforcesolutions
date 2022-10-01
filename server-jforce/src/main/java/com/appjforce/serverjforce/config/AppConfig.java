package com.appjforce.serverjforce.config;

import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;
import com.appjforce.serverjforce.model.enumeration.Role;
import com.appjforce.serverjforce.repository.PostRepository;
import com.appjforce.serverjforce.repository.UserRepository;
import com.appjforce.serverjforce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner runner (){
        return args -> {
            AppUser userAdmin = AppUser.builder()
                    .username("admin")
//                    .password(passwordEncoder.encode("admin"))
                    .password("admin")
                    .emailId("admin@admin.com")
                    .role(Role.ADMIN)
                    .phoneNo(123456789)
                    .build();

            AppUser userUser = AppUser.builder()
                    .username("user1")
//                    .password(passwordEncoder.encode("user1"))
                    .password("user1")
                    .emailId("user1@user1.com")
                    .role(Role.USER)
                    .phoneNo(123444546)
                    .build();
            userRepo.saveAll(Stream.of(userAdmin,userUser).collect(Collectors.toList()));

            UserPosts userPost1 = UserPosts.builder()
                    .userposts("User post 1")
                    .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                    .build();

            UserPosts userPost2 = UserPosts.builder()
                    .userposts("User post 2")
                    .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                    .build();

            postRepo.saveAll(Stream.of(userPost1,userPost2).collect(Collectors.toList()));
        };
    }
}
