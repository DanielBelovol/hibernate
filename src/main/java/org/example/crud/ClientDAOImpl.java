package org.example.crud;

import org.example.dao.ClientDAO;
import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.exception.CustomException;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import javax.management.Query;
import java.util.List;


public class ClientDAOImpl implements ClientDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void saveClient(Client client) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to save the client");
        }
    }

    @Override
    public Client getClient(Long id) throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, id);
            if (client == null) {
                throw new CustomException("Client not found with ID: " + id);
            }
            return client;
        } catch (Exception e) {
            throw new CustomException("Client not found with ID: " + id);
        }
    }

    @Override
    public List<Client> getAllClients() throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception e) {
            throw new CustomException("Failed to fetch all clients");
        }
    }

    @Override
    public List<Ticket> getAllTickets(Long clientId) throws CustomException {
        try(Session session = sessionFactory.openSession()){
            org.hibernate.query.Query<Ticket> query = session.createQuery("FROM Ticket t WHERE t.client.id = :clientId", Ticket.class);
            query.setParameter("clientId",clientId);
            return query.list();
        }
    }

    @Override
    public void updateClient(Long id, String name) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(name);
            } else {
                throw new CustomException("Client not found with ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to update the client with ID: " + id);
        }
    }

    @Override
    public void deleteClient(Long clientId) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.delete(client);
            } else {
                throw new CustomException("Client not found with ID: " + clientId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to delete the client with ID: " + clientId);
        }
    }

}
