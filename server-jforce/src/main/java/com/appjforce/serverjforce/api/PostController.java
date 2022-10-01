package com.appjforce.serverjforce.api;

import com.appjforce.serverjforce.model.Response;
import com.appjforce.serverjforce.model.UserPosts;
import com.appjforce.serverjforce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class PostController {

    @Autowired
    private UserService userService;

    /**
     * Api for getting all Posts from all Users as a list
     * This method is accessible to ROLE_ADMIN only
     *
     * */

    @GetMapping(path = "/get-posts")
    public ResponseEntity<Response> getAllPosts(){
        log.info("Inside getAllPosts() method of PostController");

        return ResponseEntity.ok(Response.builder()
                .message("Fetching all posts successful")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .data(Map.of("posts", userService.getAllPosts()))
                .build());
    }

    /**
     * Api for adding Posts to the Database from users
     * This method is accessible to ROLE_USERS only
     *
     * */
    @PostMapping(path = "/add-post")
    public ResponseEntity<Response> addPost(@RequestBody UserPosts userPost){
        log.info("Inside addPost() method of PostController");
        String postAddStatus = userService.addPost(userPost);
        return ResponseEntity.ok(Response.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message(postAddStatus)
                        .data(Map.of("post", userService.getPostByPost(userPost.getUserposts())))
                .build());
    }

    /**
     * Api for Updating Posts to the Database
     * This method is accessible to ROLE_ADMIN only
     *
     * */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Response> updatePost(@PathVariable("id") String id, @RequestBody UserPosts userPost){
        log.info("Inside updatePost() method of PostController");
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("postupdated", userService.updateUser(UUID.fromString(id), userPost)))
                        .message("Post Updated successfully")
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    /**
     * Api for Deleting User Posts from the Database
     * This method is accessible to ROLE_ADMIN and ROLE_USER
     *
     * */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Response> deletePost(@PathVariable("id") String id){
        log.info("Inside deletePost() method of PostController");
        userService.deletePost(UUID.fromString(id));
        return ResponseEntity.ok(
                    Response.builder()
                        .message("Deleted post successfully")
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /**
     * Api for Approving User Posts
     * This method is accessible to ROLE_ADMIN only
     *
     * */
    @PutMapping(path = "/approve/{id}")
    public ResponseEntity<Response> approveRejectPosts(@PathVariable("id") String id,
                                                       @RequestBody UserPosts userPost){
        log.info("Inside approveRejectPosts() method of PostController");

        String postApproveStatus = userService.approveRejectPost(UUID.fromString(id), userPost);

        return ResponseEntity.ok(Response.builder()
               .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
               .message(postApproveStatus)
               .status(HttpStatus.OK)
               .statusCode(HttpStatus.OK.value())
               .build());
    }
}
