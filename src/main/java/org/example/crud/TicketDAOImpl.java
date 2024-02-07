package org.example.crud;

import org.example.dao.TicketDAO;
import org.example.entities.Ticket;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void save(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getTicket(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateTicket(Ticket newUpdatedTicket) {
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
                System.err.println("Ticket not found with id: " + newUpdatedTicket.getId());
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
    public void deleteTicket(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            } else {
                System.err.println("Ticket not found with id: " + id);
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
