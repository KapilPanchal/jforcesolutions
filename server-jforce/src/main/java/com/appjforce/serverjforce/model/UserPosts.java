package com.appjforce.serverjforce.model;

import com.appjforce.serverjforce.model.enumeration.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

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
    @GeneratedValue(generator = "reviews_uuid2")
    @GenericGenerator(name = "reviews_uuid2", strategy = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private String userposts;

    private ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Z"));

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private PostStatus approved = PostStatus.NEW;
}
