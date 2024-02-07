package org.example.dao;

import org.example.entities.Client;
import org.example.entities.Planet;

import java.util.List;

public interface PlanetDAO {
    void save(Planet planet);
    Planet getPlanet(String id);
    List<Planet> getAllPlanets();
    void updatePlanet(String id, String name);
    void deletePlanet(String id);
}
