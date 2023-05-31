package ru.itis.pethome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.pethome.model.City;

import java.util.Optional;
import java.util.UUID;

public interface CityDao extends JpaRepository<City, UUID> {
    Optional<City> findByName(String name);
}
