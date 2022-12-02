package com.david.auth.services;

import com.david.auth.entities.Client;
import com.david.auth.entities.Role;
import com.david.auth.entities.RoleClient;
import com.david.auth.repo.ClientRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepo clientRepo;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepo clientRepo, RoleServiceImpl roleService, PasswordEncoder passwordEncoder){
        this.clientRepo = clientRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client createClient(Client client) throws Exception {
        // Antes de guardar verificar si ya existe una persona con el mismo correo o username
        client.setPassword(this.passwordEncoder.encode(client.getPassword()));
        Client client_save = this.clientRepo.save(client);
        Role role = this.roleService.findById(1).get();
        this.roleService.createRoleClient(new RoleClient(role, client_save));
        return this.clientRepo.findById(client_save.getCodigo()).get();
    }

    @Override
    public Client findClientByUsername(String username) throws Exception {
        return this.clientRepo.findByUsername(username);
    }
}
