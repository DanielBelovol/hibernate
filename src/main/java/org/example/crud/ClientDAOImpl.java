package org.example.crud;

import org.example.dao.ClientDAO;
import org.example.entities.Client;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class ClientDAOImpl implements ClientDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();



    @Override
    public void saveClient(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Client getClient(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateClient(Long id, String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(name);
            } else {
                // Лучше выбросить исключение или вернуть значение, указывающее на ошибку
                System.err.println("Client not found with id: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(Long clientId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.delete(client);
            } else {
                // Аналогично update, лучше уведомить о том, что клиент не найден
                System.err.println("Client not found with id: " + clientId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
