package com.library.Library.security;

import com.library.Library.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u =new User ("admin", "$2a$10$dUJ4f9I4bldbDK03Ci1g3.abCSCkcEwl1NOxZs2NllVdsA44VSnOa");
        return new UserDetailsImpl(u);
    }
}
