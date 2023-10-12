package com.easybankjakarta.Services;

import com.easybankjakarta.Dao.ClientDao;
import com.easybankjakarta.Models.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientDao clientDao;
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Optional<Client> addClient(Client client) {
        Optional<Client> optionalClient = clientDao.addClient(client);
        if (optionalClient.isPresent()) {
            return optionalClient;
        } else {
            return Optional.empty();
        }
    }

    public Integer deleteClient(String code) {
        Integer res = clientDao.deleteClient(code);
        if (res != 0) {
            return res;
        } else {
            return res;
        }
    }

    public Optional<Client> updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    public Optional<Client> getClient(String code) {
        Optional<Client> optionalClient = clientDao.getClient(code);
        if (optionalClient.isPresent()) {
            return optionalClient;
        } else {
            return Optional.empty();
        }
    }

    public List<Client> getClients() {
        List<Client> clients = clientDao.getClients();
        return clients;
    }
}
