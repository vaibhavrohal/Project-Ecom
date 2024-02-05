package com.scaler.ecomuserservice.service;

import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.model.Role;
import com.scaler.ecomuserservice.model.User;
import com.scaler.ecomuserservice.repository.RoleRepository;
import com.scaler.ecomuserservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public UserDto getUserDetail(Long userId){
           Optional<User> optionalUser=userRepository.findById(userId);
           if (optionalUser.isEmpty()){
               throw new RuntimeException("User not found with userId"+userId);
           }
           User user=optionalUser.get();
           return UserDto.from(user);
    }

    public UserDto setUserRoles(Long userId, List<Long> roles){
          Optional<User> optionalUser=userRepository.findById(userId);
          Set<Role> rolelist=roleRepository.findAllByIdIn(roles);

          if (optionalUser.isEmpty()){
              throw new RuntimeException("User not found with user Id"+userId);
          }
          User user=optionalUser.get();
          user.setRolls(rolelist);

          User savedUser=userRepository.save(user);

        return UserDto.from(savedUser);
    }
}
