package com.scaler.ecomuserservice.service;


import com.scaler.ecomuserservice.model.Role;
import com.scaler.ecomuserservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name){
       Role role=new Role();
        role.setRollName(name);

        return  roleRepository.save(role);
    }
}
