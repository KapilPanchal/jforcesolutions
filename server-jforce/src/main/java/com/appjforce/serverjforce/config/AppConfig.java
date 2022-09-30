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

    @Bean
    CommandLineRunner runner (){
        return args -> {
            AppUser userAdmin = AppUser.builder()
                    .username("admin")
                    .password("admin")
                    .emailId("admin@admin.com")
                    .role(Role.ROLE_ADMIN)
                    .phoneNo(123456789)
                    .build();

            AppUser userUser = AppUser.builder()
                    .username("user1")
                    .password("user1")
                    .emailId("user1@user1.com")
                    .role(Role.ROLE_USER)
                    .phoneNo(123444546)
                    .build();
            userRepo.saveAll(Stream.of(userAdmin,userUser).collect(Collectors.toList()));

            UserPosts userPost1 = UserPosts.builder()
                    .userposts("User post 1")
                    .build();

            UserPosts userPost2 = UserPosts.builder()
                    .userposts("User post 2")
                    .build();

            postRepo.saveAll(Stream.of(userPost1,userPost2).collect(Collectors.toList()));

//            String postAddStatus1 = userService.addPostToUser(userPost1.getUserposts(), userAdmin.getUsername());
//            String postAddStatus2 = userService.addPostToUser(userPost2.getUserposts(), userUser.getUsername());
        };
    }
}
