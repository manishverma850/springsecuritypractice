package com.mv.security.demo.userdetail;

import com.mv.security.demo.entity.userentity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(com.mv.security.demo.entity.userentity.User user) {

        this.user = user;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

//    @Override
//    public boolean isEnabled() {
//        return user.isEnabled();
//    }
}
