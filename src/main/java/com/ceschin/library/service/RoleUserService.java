package com.ceschin.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceschin.library.model.Role;
import com.ceschin.library.model.User;
import com.ceschin.library.service.Dto.UserRoleDto;

@Service
public class RoleUserService {

    @Autowired
    UserService userService;

    public User setUserRole(UserRoleDto userRoleDto) {
        User userExistente = userService.listarUserById(userRoleDto.getIdUser());
        List<Role> roles = new ArrayList<>();

        roles = userRoleDto.getIdRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        userExistente.setListRoles(roles);

        userService.salvarUser(userExistente);
        return userExistente;

    }

}
