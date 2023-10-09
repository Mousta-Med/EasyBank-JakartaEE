package com.easybankjakarta.Dao;

import com.easybankjakarta.Models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao {
    Optional<Client> addClient(Client client);
    Integer deleteClient(String code);
    Optional<Client> updateClient(Client client);
    Optional<Client> getClient(String code);
    Optional<Client> getClientByAttribut(String string);
    List<Client> getClients();
}
