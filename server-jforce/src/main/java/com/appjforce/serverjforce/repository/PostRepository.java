package com.appjforce.serverjforce.repository;

import com.appjforce.serverjforce.model.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<UserPosts, Long> {

    @Query(value = "SELECT * FROM appjforcesch.userpost WHERE userposts = ?1",
            nativeQuery = true)
    Optional<UserPosts> getByPost(String userpost);

    @Query(value = "UPDATE appjforcesch.userpost SET userposts = ?2 WHERE id = ?1",
            nativeQuery = true)
    @Modifying
    @Transactional
    void saveById(long id, String userpost);
}
