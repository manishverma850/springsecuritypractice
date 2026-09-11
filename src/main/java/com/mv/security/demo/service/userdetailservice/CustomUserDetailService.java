package com.mv.security.demo.service.userdetailservice;

import com.mv.security.demo.entity.userentity.User;
import com.mv.security.demo.repository.UserRepository;
import com.mv.security.demo.userdetail.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username).get();
       return new CustomUserDetail(user);
    }
}
