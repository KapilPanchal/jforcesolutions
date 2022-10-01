package com.appjforce.serverjforce.repository;

import com.appjforce.serverjforce.model.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<UserPosts, UUID> {

    @Query(value = "SELECT * FROM appjforcesch.userpost WHERE userposts = ?1",
            nativeQuery = true)
    Optional<UserPosts> getByPost(String userpost);

    @Query(value = "UPDATE appjforcesch.userpost SET userposts = ?2 WHERE id = ?1",
            nativeQuery = true)
    @Modifying
    @Transactional
    void saveById(String id, String userpost);

    @Query(value = "UPDATE appjforcesch.userpost SET approved=?2 WHERE id= ?1",
            nativeQuery = true)
    @Modifying
    @Transactional
    void updateUserPostSetPost(String id, String status);
}

//    @Query(value = "UPDATE appjforcesch.userpost SET approved=?2 WHERE id= ?1")
//    @Modifying
//    @Transactional
//    void saveByUserPosts(long id, String status);
//    @Query(value = "UPDATE appjforcesch.userpost SET approved=?2 WHERE id= ?1")
//    @Modifying
//    @Transactional
//    void updateUserPostSetPost(long id, String status);