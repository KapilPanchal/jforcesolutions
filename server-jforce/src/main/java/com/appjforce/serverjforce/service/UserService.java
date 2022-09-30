package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;

import java.util.List;

public interface UserService {
    List<AppUser> getAllUsers();

    String addUser(AppUser appuser);

    List<UserPosts> getAllPosts();

    String addPost(UserPosts userPost);

    AppUser getUserByUsername(String username);

    UserPosts getPostByPost(String userpost);

    UserPosts updateUser(long id, UserPosts userpost);

    void deletePost(Long id);
}
