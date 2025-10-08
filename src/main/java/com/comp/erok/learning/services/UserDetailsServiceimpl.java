package com.comp.erok.learning.services;

import com.comp.erok.learning.Model.User;
import com.comp.erok.learning.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceimpl implements UserDetailsService {
    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // ensure roles is not null
        String[] roles = (user.getRoles() != null && !user.getRoles().isEmpty())
                ? user.getRoles().toArray(new String[0])
                : new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
