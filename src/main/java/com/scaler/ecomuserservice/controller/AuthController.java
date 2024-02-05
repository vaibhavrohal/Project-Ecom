package com.scaler.ecomuserservice.controller;

import com.scaler.ecomuserservice.dto.LoginRequestDto;
import com.scaler.ecomuserservice.dto.SignUpRequestDto;
import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.dto.ValidateTokenRequestDto;
import com.scaler.ecomuserservice.model.Session;
import com.scaler.ecomuserservice.model.SessionStatus;
import com.scaler.ecomuserservice.model.User;
import com.scaler.ecomuserservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto requestDto){
        return authService.login(requestDto.getEmail(),requestDto.getPassword());
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable ("id") Long userid,@RequestHeader("token") String token){
        return authService.logout(token,userid);
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto request){
        UserDto userDto= authService.signUp(request.getEmail(),request.getPassword(),request.getProfilename());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request){
        SessionStatus sessionStatus=authService.validate(request.getToken(), request.getUserId());
        return new ResponseEntity<>(sessionStatus,HttpStatus.OK);
    }

    //below APIs are only for learning purposes, should not be present in actual systems
    @GetMapping ("/session")
    public ResponseEntity<List<Session>> getAllSession(){
        return authService.getAllSession();
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return authService.getAllUsers();
    }
    @GetMapping("hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello Wold");
    }
}
