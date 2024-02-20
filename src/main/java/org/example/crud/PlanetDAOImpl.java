package org.example.crud;

import org.example.dao.PlanetDAO;
import org.example.entities.Planet;
import org.example.exception.CustomException;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class PlanetDAOImpl implements PlanetDAO {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void save(Planet planet) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to save the planet");
        }
    }

    @Override
    public Planet getPlanet(String id) throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid planet ID format: " + id);
        } catch (Exception e) {
            throw new CustomException("Failed to fetch the planet");
        }
    }

    @Override
    public List<Planet> getAllPlanets() throws CustomException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        } catch (Exception e) {
            throw new CustomException("Failed to fetch the planet list");
        }
    }

    @Override
    public void updatePlanet(String id, String name) throws CustomException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
            } else {
                throw new CustomException("Planet not found with id: " + id);
            }
            transaction.commit();
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid planet ID format: " + id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to update the planet");
        }
    }

    @Override
    public void deletePlanet(String id) throws CustomException {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
                transaction.commit();
            } else {
                throw new CustomException("Planet not found with id: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("Failed to delete the planet");
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

