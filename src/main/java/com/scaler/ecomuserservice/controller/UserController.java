package com.scaler.ecomuserservice.controller;

import com.scaler.ecomuserservice.dto.SetUserRolesRequestDto;
import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.model.User;
import com.scaler.ecomuserservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
  private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetail(@PathVariable ("id")Long userid){
        UserDto userDto=userService.getUserDetail(userid);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id")Long userId,@RequestBody SetUserRolesRequestDto request ){
       UserDto userDto=userService.setUserRoles(userId,request.getRoleIds());
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
