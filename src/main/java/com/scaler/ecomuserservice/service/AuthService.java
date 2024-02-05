package com.scaler.ecomuserservice.service;

import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.model.Session;
import com.scaler.ecomuserservice.model.SessionStatus;
import com.scaler.ecomuserservice.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    public ResponseEntity<List<Session>> getAllSession();
    public ResponseEntity<List<User>> getAllUsers();
    public ResponseEntity <UserDto> login(String email, String password);
    public ResponseEntity<Void> logout(String token,Long userId);
    public UserDto signUp(String email,String password,String profilename);
    public SessionStatus validate(String token, Long userId);
}
