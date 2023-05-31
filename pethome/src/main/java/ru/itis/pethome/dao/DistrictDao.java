package ru.itis.pethome.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistrictDao extends JpaRepository<District, UUID> {

    @Query("select d from District d inner join City c on c.id=d.city.id where d.name = :district and c.name = :city")
    District getDistrictByNameAndCity(String district, String city);

    List<District> getDistrictByCity(City city);
}
