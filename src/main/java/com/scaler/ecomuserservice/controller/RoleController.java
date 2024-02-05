package com.scaler.ecomuserservice.controller;

import com.scaler.ecomuserservice.dto.CreateRoleRequestDto;
import com.scaler.ecomuserservice.model.Role;
import com.scaler.ecomuserservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/role")
    public ResponseEntity<Role> createRoll(@RequestBody CreateRoleRequestDto request){
         Role role=roleService.createRole(request.getName());
         return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
