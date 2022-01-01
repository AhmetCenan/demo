package com.example.Demo.Security.services;

import com.example.Demo.Model.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        List<GrantedAuthority> authorities  = new ArrayList<>();
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return user.getUserId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {return isEnabled();}

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UserDetailsImpl user = (UserDetailsImpl)o;
            return this.getId().equals(user.getId());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getId()});
    }
}
