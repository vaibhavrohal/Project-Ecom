package com.scaler.ecomuserservice.dto;

import com.scaler.ecomuserservice.model.Role;
import com.scaler.ecomuserservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private String profileName;
    private Set<Role> roles=new HashSet<>();

    public  static UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setProfileName(user.getProfileName());
        userDto.setRoles(user.getRolls());

        return userDto;
    }
}
