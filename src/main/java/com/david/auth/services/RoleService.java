package com.david.auth.services;

import com.david.auth.entities.Role;
import com.david.auth.entities.RoleClient;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Integer codigo);
    List<RoleClient> findAllClientRolesByUsername(String username);
    List<Role> findClientRolesByUsername(String username);
    Role createRole(Role role);
    RoleClient createRoleClient(RoleClient roleClient);
}
