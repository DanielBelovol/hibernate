package org.example.crud;

import org.example.dao.TicketDAO;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.exception.CustomException;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void save(Ticket ticket) throws CustomException {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new CustomException("Cannot save a ticket with null client or planet");
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            if (!existsById(Client.class, String.valueOf(ticket.getClient().getId())) ||
                    !existsById(Planet.class, ticket.getFromPlanet().getId()) ||
                    !existsById(Planet.class, ticket.getToPlanet().getId())) {
                throw new CustomException("Client or planet does not exist");
            }
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to save client");
        }
    }

    private boolean existsById(Class<?> entityClass, String id) {
        String entityName = entityClass.getSimpleName();
        String hql = "SELECT count(e.id) FROM " + entityName + " e WHERE e.id = :entityId";
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("entityId", id);
            Long count = query.uniqueResult();
            return count > 0;
        }
    }

    @Override
    public Ticket getTicket(Long id) throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new CustomException("Failed to fetch the ticket");
        }
    }

    @Override
    public List<Ticket> getAllTickets() throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            throw new CustomException("Failed to fetch the ticket list");
        }
    }

    @Override
    public void updateTicket(Ticket newUpdatedTicket) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, newUpdatedTicket.getId());
            if (ticket != null) {
                ticket.setClient(newUpdatedTicket.getClient());
                ticket.setCreatedAt(newUpdatedTicket.getCreatedAt());
                ticket.setFromPlanet(newUpdatedTicket.getFromPlanet());
                ticket.setToPlanet(newUpdatedTicket.getToPlanet());
            } else {
                throw new CustomException("Ticket not found with id:" + newUpdatedTicket.getId());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to update the ticket");
        }
    }

    @Override
    public void deleteTicket(Long id) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            } else {
                throw new CustomException("Ticket not found with id:" + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to delete the ticket");
        }
    }
}
