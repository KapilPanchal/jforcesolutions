package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.exceptions.CustomUserException;
import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;
import com.appjforce.serverjforce.repository.PostRepository;
import com.appjforce.serverjforce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<AppUser> getAllUsers() {
        log.info("Inside getAllUsers() method of UserService");
        List<AppUser> usersList;
        try{
          usersList = userRepo.findAll();
        } catch (Exception e){
            throw new CustomUserException("Users List not found!");
        }
        return usersList;
    }

    @Override
    public String addUser(AppUser appuser) {
        log.info("Inside addUser() method of UserController");
        try {
            userRepo.saveAndFlush(appuser);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "User Added Successfully";
    }

    @Override
    public List<UserPosts> getAllPosts() {
        log.info("Inside getAllPosts() method of UserService");
        List<UserPosts> postList;
        try {
            postList = postRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return postList;
    }

    @Override
    public String addPost(UserPosts userPost) {
        log.info("Inside addPost() method of UserService");
        try {
            postRepo.saveAndFlush(userPost);
        } catch(Exception e) {
            throw new CustomUserException(e.getMessage());
        }
        return "Post Added Successfully";
    }

    @Override
    public AppUser getUserByUsername(String username) {
        log.info("Inside getUserByUsername() method of UserService");
        return userRepo.getByUsername(username).get();
    }

    @Override
    public UserPosts getPostByPost(String userpost) {
        log.info("Inside getPostByPost() method of UserService");
        UserPosts userPost;
        try {
            userPost = postRepo.getByPost(userpost).get();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return userPost;
    }

    @Override
    public UserPosts updateUser(long id, UserPosts userpost) {
        log.info("Inside updateUser() method of UserService");
        try {
            postRepo.saveById(id, userpost.getUserposts());
        } catch (Exception e){
            throw new CustomUserException(e.getMessage());
        }
        return userpost;
    }

    @Override
    public void deletePost(Long id) {
        log.info("Inside deletePost() method of UserService");
        try {
            postRepo.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new CustomUserException(e.getMessage());
        }
    }
}
