package com.david.auth.repo;

import com.david.auth.entities.Role;
import com.david.auth.entities.RoleClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleClientRepo extends JpaRepository<RoleClient,Integer> {
    @Query("SELECT rc from Client c join RoleClient rc on c.codigo = rc.client.codigo join Role r on r.codigo = rc.role.codigo WHERE c.username = :username")
    List<RoleClient> findAllByUsername(String username);

    @Query("SELECT r from Client c join RoleClient rc on c.codigo = rc.client.codigo join Role r on r.codigo = rc.role.codigo WHERE c.username = :username")
    List<Role> findAllRolesByUsername(String username);
}
