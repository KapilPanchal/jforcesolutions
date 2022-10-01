package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<AppUser> getAllUsers();

    String addUser(AppUser appuser);

    List<UserPosts> getAllPosts();

    String addPost(UserPosts userPost);

    AppUser getUserByUsername(String username);

    UserPosts getPostByPost(String userpost);

    UserPosts updateUser(UUID id, UserPosts userpost);

    void deletePost(UUID id);

    String approveRejectPost(UUID id, UserPosts userPosts);

    String changeUserRole(UUID id, AppUser appUser);
}
