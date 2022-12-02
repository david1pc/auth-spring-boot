package com.david.auth.services;

import com.david.auth.entities.Role;
import com.david.auth.entities.RoleClient;
import com.david.auth.repo.RoleClientRepo;
import com.david.auth.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;
    private final RoleClientRepo roleClientRepo;

    public RoleServiceImpl(RoleRepo roleRepo, RoleClientRepo roleClientRepo){
        this.roleRepo = roleRepo;
        this.roleClientRepo = roleClientRepo;
    }

    @Override
    public List<RoleClient> findAllClientRolesByUsername(String username) {
        return this.roleClientRepo.findAllByUsername(username);
    }

    @Override
    public List<Role> findClientRolesByUsername(String username) {
        return this.roleClientRepo.findAllRolesByUsername(username);
    }

    @Override
    public Role createRole(Role role) {
        return this.roleRepo.save(role);
    }

    @Override
    public RoleClient createRoleClient(RoleClient roleClient) {
        return this.roleClientRepo.save(roleClient);
    }

    @Override
    public Optional<Role> findById(Integer codigo){
        return this.roleRepo.findById(codigo);
    }
}
