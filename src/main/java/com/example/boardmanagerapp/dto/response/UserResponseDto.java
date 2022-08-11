package com.example.boardmanagerapp.dto.response;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String email;
    List<Long> rolesIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getRolesIds() {
        return rolesIds;
    }

    public void setRolesIds(List<Long> rolesIds) {
        this.rolesIds = rolesIds;
    }

    @Override
    public String toString() {
        return "UserResponseDto{"
                + "id=" + id
                + ", email='" + email + '\''
                + ", rolesIds=" + rolesIds
                + '}';
    }
}
