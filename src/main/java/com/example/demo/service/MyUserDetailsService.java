package com.example.demo.service;

import com.example.demo.data.Entity.User;
import com.example.demo.data.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }
        User user = userRepository.findByName(username);
        return new MyUserDetails(user, getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        if (user.isAdmin()) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }

    @Transactional
    public void registerUser(String username, String password, String email) {
        User user = new User(username, passwordEncoder.encode(password), email, false);
        userRepository.save(user);
    }

    @Transactional
    public void registerAdmin(String username, String password, String email) {
        User user = new User(username, passwordEncoder.encode(password), email, true);
        userRepository.save(user);
    }
}
