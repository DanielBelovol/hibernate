package org.example.dao;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.exception.CustomException;

import java.util.List;

public interface PlanetDAO {
    void save(Planet planet) throws CustomException;
    Planet getPlanet(String id) throws CustomException;
    List<Planet> getAllPlanets() throws CustomException;
    void updatePlanet(String id, String name) throws CustomException;
    void deletePlanet(String id) throws CustomException;
}
