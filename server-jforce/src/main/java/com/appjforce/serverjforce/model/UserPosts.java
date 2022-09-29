package com.appjforce.serverjforce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "UserPost")
@Table (name = "userpost",
        catalog = "appjforcecat",
        schema = "appjforcesch",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPosts implements Serializable {

    @Id
    @SequenceGenerator(name = "app_posts_sequence",
            sequenceName = "app_posts_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "app_posts_sequence")
    private long id;

    @Column(nullable = false)
    private String userposts;
}
