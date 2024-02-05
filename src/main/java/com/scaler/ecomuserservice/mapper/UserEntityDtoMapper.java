package com.scaler.ecomuserservice.mapper;

import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.model.User;

public class UserEntityDtoMapper {
    public static UserDto getUserDtofromUserEntity(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setProfileName(user.getProfileName());
        userDto.setRoles(user.getRolls());

        return userDto;
    }
}
