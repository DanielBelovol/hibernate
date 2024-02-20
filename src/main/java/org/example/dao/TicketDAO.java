package org.example.dao;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.exception.CustomException;

import java.util.List;

public interface TicketDAO {
    void save(Ticket ticket) throws CustomException;
    Ticket getTicket(Long id) throws CustomException;
    List<Ticket> getAllTickets() throws CustomException;
    void updateTicket(Ticket newUpdatedTicket) throws CustomException;
    void deleteTicket(Long id) throws CustomException;
}
