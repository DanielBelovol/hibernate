package org.example.crud;

import org.example.dao.PlanetDAO;
import org.example.entities.Planet;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class PlanetDAOImpl implements PlanetDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void save(Planet planet) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Planet getPlanet(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        } catch (NumberFormatException e) {
            System.err.println("Invalid planet ID format: " + id);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Planet> getAllPlanets() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePlanet(String id, String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
            } else {
                System.err.println("Planet not found with id: " + id);
            }
            transaction.commit();
        } catch (NumberFormatException e) {
            System.err.println("Invalid planet ID format: " + id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlanet(String id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Planet planet = session.get(Planet.class, id);
        if (planet != null) {



            session.delete(planet);
        }

        transaction.commit();
        session.close();
    }
}
