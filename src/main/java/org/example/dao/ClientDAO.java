package org.example.dao;

import org.example.entities.Client;

import java.util.List;

public interface  ClientDAO {
    void saveClient(Client client);
    Client getClient(Long id);
    List<Client> getAllClients();
    void updateClient(Long id, String name);
    void deleteClient(Long id);
}
