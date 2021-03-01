package com.modoodesigner.domain.model.user;

import com.modoodesigner.domain.common.model.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Getter
@EqualsAndHashCode
@ToString
public class SimpleUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 1004094589655850106L;

    private UserId userId;
    private String username;
    private String password;
    private Set<Role> roles;

    public SimpleUser(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            rolesList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return rolesList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
