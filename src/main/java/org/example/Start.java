package org.example;

import org.example.crud.ClientDAOImpl;
import org.example.crud.PlanetDAOImpl;
import org.example.crud.TicketDAOImpl;
import org.example.hibernate.HibernateUtil;
import org.example.migrate.MigrationClass;

public class Start {
    public static void main(String[] args) {
        MigrationClass migrationClass = new MigrationClass();
        migrationClass.migrate();

        PlanetDAOImpl planetDAO = new PlanetDAOImpl();
        planetDAO.deletePlanet("VEN");
        System.out.println(planetDAO.getAllPlanets().toString());

        ClientDAOImpl clientDAO = new ClientDAOImpl();
        clientDAO.deleteClient(1L);
        System.out.println(clientDAO.getAllClients().toString());

        TicketDAOImpl ticketDAO = new TicketDAOImpl();
        System.out.println(ticketDAO.getAllTickets().toString());



    }
}
