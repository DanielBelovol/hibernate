package org.example.dao;

import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.exception.CustomException;

import java.util.List;

public interface  ClientDAO {
    void saveClient(Client client) throws CustomException;
    Client getClient(Long id) throws CustomException;
    List<Client> getAllClients() throws CustomException;
    List<Ticket> getAllTickets(Long clientId) throws CustomException;
    void updateClient(Long id, String name) throws CustomException;
    void deleteClient(Long id) throws CustomException;
}
