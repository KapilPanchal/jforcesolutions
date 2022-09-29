package com.appjforce.serverjforce.model;

import com.appjforce.serverjforce.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "AppUser")
@Table(name = "appuser",
        catalog = "appjforcecat",
        schema = "appjforcesch",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser implements Serializable {

    @Id
    @SequenceGenerator(name = "app_user_sequence",
                        sequenceName = "app_user_sequence",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                        generator = "app_user_sequence")
    private long id;

    @NotEmpty(message = "Name cannot be empty.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 150)
    private String username;

    @NotEmpty(message = "Password cannot be empty.")
    @Column(nullable = false)
    @Size(min = 3, max = 250)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    @NotEmpty(message = "Email cannot be Empty")
    @Size(max = 250)
    private String emailId;

    @Column(nullable = false)
    private int phoneNo;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    @JoinTable(name = "app_user_posts",
            joinColumns = @JoinColumn (
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn (
                    name = "post_id",
                    referencedColumnName = "id"
            ),
            catalog = "appjforcesch",
            schema = "appjforcesch")
    @Builder.Default
    private Collection<UserPosts> userPosts = new ArrayList<>();
}
