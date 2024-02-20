package org.example;

import org.example.crud.ClientDAOImpl;
import org.example.crud.PlanetDAOImpl;
import org.example.crud.TicketDAOImpl;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.exception.CustomException;
import org.example.migrate.MigrationClass;

import java.util.List;


public class Start {
    public static void main(String[] args) throws CustomException {
        MigrationClass migrationClass = new MigrationClass();
        migrationClass.migrate();

        TicketDAOImpl ticketDAO = new TicketDAOImpl();
        ticketDAO.save(new Ticket(21L, new Planet("VEN","Venus"), new Planet("MARS","Mars")));

        ClientDAOImpl clientDAO = new ClientDAOImpl();
        List<Ticket> allTickets = clientDAO.getAllTickets(2L);

        System.out.println(allTickets);
    }
}
