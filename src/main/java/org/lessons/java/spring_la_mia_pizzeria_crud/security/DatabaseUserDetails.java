package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import java.util.HashSet;
import java.util.Set;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Role;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> permissions;

    public DatabaseUserDetails(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        permissions = new HashSet<GrantedAuthority>();

        for (Role userRole : user.getRoles()) {
            permissions.add(new SimpleGrantedAuthority(userRole.getName()));
        }
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public Integer getId() {
        return this.id;
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
