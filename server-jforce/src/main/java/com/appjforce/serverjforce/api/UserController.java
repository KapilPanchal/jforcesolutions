package com.appjforce.serverjforce.api;

import com.appjforce.serverjforce.model.AppUser;
import com.appjforce.serverjforce.model.Response;
import com.appjforce.serverjforce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Api for getting all users from the database
     * This method is accessible to ROLE_ADMIN only
     *
     * */
    @GetMapping(path = "/get-users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response> getAllUsers(){
        log.info("Inside getAllUsers() method of UserController");

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                        .message("Retrieve all users Successful!")
                        .status(HttpStatus.FOUND)
                        .statusCode(HttpStatus.FOUND.value())
                        .data(Map.of("users", userService.getAllUsers()))
                        .build()
        );
    }

    /**
     * Api for Adding user to the database
     * This method is accessible to ROLE_USER and ROLE_ADMIN only
     *
     * */
    @PostMapping(path = "/add-user")
    public ResponseEntity<Response> addUser(@RequestBody AppUser appuser){
        log.info("Inside addUser() method of UserController");
        String addStatus = userService.addUser(appuser);

        return ResponseEntity.ok(Response.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message(addStatus)
                        .data(Map.of("user", appuser))
                .build());
    }

    /**
     * Admin should be able to create other admin
     * when a new user is created default role
     * assigned to the user is ROLE_USER
     * */
    @PutMapping(path = "change-role/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response> changeUserRole(@PathVariable("id") String id,
                                                   @RequestBody AppUser appUser){
        log.info("Inside changeUserRole() method of UserController");

        String statusChangeRole = userService.changeUserRole(UUID.fromString(id), appUser);

        return ResponseEntity.ok(Response.builder()
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message(statusChangeRole)
                .build());
    }
}
