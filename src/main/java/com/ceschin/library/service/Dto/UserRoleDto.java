package com.ceschin.library.service.Dto;

import java.util.List;
import java.util.UUID;

public class UserRoleDto {
    private UUID idUser;
    private List<UUID> idRoles;

    public UserRoleDto() {
    }

    public UserRoleDto(UUID idUser, List<UUID> idRoles) {
        this.idUser = idUser;
        this.idRoles = idRoles;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public List<UUID> getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(List<UUID> idRoles) {
        this.idRoles = idRoles;
    }

}
