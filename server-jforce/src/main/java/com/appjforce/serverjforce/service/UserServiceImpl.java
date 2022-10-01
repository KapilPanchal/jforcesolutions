package com.appjforce.serverjforce.service;

import com.appjforce.serverjforce.exceptions.CustomUserException;
import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.UserPosts;
import com.appjforce.serverjforce.model.enumeration.PostStatus;
import com.appjforce.serverjforce.model.enumeration.Role;
import com.appjforce.serverjforce.repository.PostRepository;
import com.appjforce.serverjforce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
            appuser.setUsername(appuser.getUsername().toLowerCase(Locale.ROOT));
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
    public UserPosts updateUser(UUID id, UserPosts userpost) {
        log.info("Inside updateUser() method of UserService");
        try {
            postRepo.saveById(id, userpost.getUserposts());
        } catch (Exception e){
            throw new CustomUserException(e.getMessage());
        }
        return userpost;
    }

    @Override
    public void deletePost(UUID id) {
        log.info("Inside deletePost() method of UserService");
        try {
            postRepo.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new CustomUserException(e.getMessage());
        }
    }

    @Override
    public String approveRejectPost(UUID id, UserPosts userPosts) {
        log.info("Inside approveRejectPost() method of PostService");

        if (userPosts.getApproved().toString().toUpperCase() == PostStatus.APPROVED.toString()) {
            userPosts.setApproved(PostStatus.APPROVED);
            System.err.println(PostStatus.APPROVED);
        } else if (userPosts.getApproved().toString().toUpperCase() == PostStatus.REJECTED.toString()) {
            userPosts.setApproved(PostStatus.REJECTED);
        } else {
            throw new CustomUserException("Approval status can either be Approved or Rejected");
        }
        try{
            postRepo.updateUserPostSetPost(id, userPosts.getApproved().toString());

        } catch(DataIntegrityViolationException e) {
            new CustomUserException(e.getMessage());
        }
        return "Approve/Reject Successful";
    }

    @Override
    public String changeUserRole(UUID id, AppUser appUser) {
        log.info("Inside changeUserRole() method of UserService");
        if(appUser.getRole().toString().toUpperCase() == Role.ADMIN.toString()){
            appUser.setRole(Role.ADMIN);
        } else if(appUser.getRole().toString().toUpperCase() == Role.USER.toString()) {
            appUser.setRole(Role.USER);
        } else {
            throw new CustomUserException("Role does not exist");
        }
        try {
            userRepo.updateUserByRole(id, appUser.getRole().toString().toUpperCase());
        } catch (DataIntegrityViolationException e) {
            throw new CustomUserException(e.getMessage());
        }
        return "User Role Updated Successfully";
    }
}
