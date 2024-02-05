package com.scaler.ecomuserservice.security;

import com.scaler.ecomuserservice.model.User;
import com.scaler.ecomuserservice.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomSpringUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomSpringUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User details with given username not found");
        }
        User saveduser=user.get();
        return new CustomSpringUserDetail(saveduser);
    }
}
