package com.appjforce.serverjforce.model;

import com.appjforce.serverjforce.model.enumeration.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

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
//    @SequenceGenerator(name = "app_posts_sequence",
//            sequenceName = "app_posts_sequence",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "app_posts_sequence")
    @GeneratedValue(generator = "reviews_uuid2")
    @GenericGenerator(name = "reviews_uuid2", strategy = "uuid2")
    private long id;

    @Column(nullable = false)
    private String userposts;

    private ZonedDateTime timestamp;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private PostStatus approved = PostStatus.NEW;
}
