package org.example.crud;

import org.example.entities.Clients;
import org.example.hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ClientCrudService {
    public void createClient(Clients client) {
        try (Session session = (Session) HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(client);

            tx.commit();
        }
    }

    public Clients getClientById(Long id) {
        if (id < 0) {
            return null;
        }
        try (Session session = (Session) HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Clients newClient = session.find(Clients.class, id);
            tx.commit();
            return newClient;
        }
    }

    public void updateClient(Clients client) {
        try (Session session = (Session) HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Clients clientFromDatabase = session.find(Clients.class, client.getId());
            clientFromDatabase.setName(client.getName());

            tx.commit();
        }
    }

    public void deleteClient(Long id) {
        try (Session session = (Session) HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Query q = session.createQuery("delete Client where id = :id").setParameter("id", id);
            q.executeUpdate();

            tx.commit();
        }
    }

    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        System.out.println(clientCrudService.getClientById(1L).toString());
    }
}
