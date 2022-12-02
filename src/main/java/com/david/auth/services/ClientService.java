package com.david.auth.services;

import com.david.auth.entities.Client;

public interface ClientService {
    public Client createClient(Client client)throws Exception;
    public Client findClientByUsername(String username)throws Exception;
}
