package com.scaler.ecomuserservice.service;

import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.model.Role;

import java.util.List;

public interface UserService {
    public UserDto getUserDetail(Long userId);
    public UserDto setUserRoles(Long userId, List<Long> roles);
}
