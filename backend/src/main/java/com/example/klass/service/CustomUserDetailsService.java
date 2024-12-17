package com.example.klass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.klass.model.UserPrincipal;
import com.example.klass.model.Users;
import com.example.klass.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    // @Autowired
    // private UserRepository userRepository;

    // public boolean authenticate(String username, String password) {
    //     User user = userRepository.findByUsername(username);
    //     return user != null && user.getPassword().equals(password);
    // }
}
