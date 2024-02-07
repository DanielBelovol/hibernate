package org.example.dao;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;

import java.util.List;

public interface TicketDAO {
    void save(Ticket ticket);
    Ticket getTicket(Long id);
    List<Ticket> getAllTickets();
    void updateTicket(Ticket newUpdatedTicket);
    void deleteTicket(Long id);
}
