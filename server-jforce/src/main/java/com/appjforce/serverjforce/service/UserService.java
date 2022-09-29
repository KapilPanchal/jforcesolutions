package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;

import java.util.List;

public interface UserService {
    List<AppUser> getAllUsers();

    String addPostToUser(String userpost, String username);

    String addUser(AppUser appuser);

    List<UserPosts> getAllPosts();

    String addPost(UserPosts userPost);

    AppUser getUserByUsername(String username);

    UserPosts getPostByPost(String userpost);

    UserPosts updateUser(UserPosts userpost);

    void deletePost(Long id);
}
