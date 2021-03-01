package com.modoodesigner.domain.model.user;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.common.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -5108186504017368674L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name ="USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
            )
    private Set<Role> roles = new HashSet<>();

    public void addRoles(Role role) {
        roles.add(role);
    }

    public static User create(String username, String emailAddress, String password) {
        User user = new User();
        user.username = username;
        user.emailAddress = emailAddress;
        user.password = password;
        Role role = new Role("ROLE_USER");
        user.roles.add(role);
        return user;
    }


    public UserId getUserId() {
        return new UserId(id);
    }

}
