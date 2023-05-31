package ru.itis.pethome.service;

import ru.itis.pethome.dto.request.DistrictRequest;
import ru.itis.pethome.dto.response.CityResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;

import java.util.List;
import java.util.Optional;

public interface DistrictService {

    District createDistrict(District district);

    District updateDistrict(District district);


    Optional<District> getDistrictByName(DistrictRequest district);

    List<DistrictResponse> getDistrictByCity(String city);
    Optional<District> resolutionDistrict(DistrictRequest districtRequest);

    void deleteDistrict(District district);

    City createCity(City city);

    City updateCity(City city);

    City deleteCity(City city);

    List<CityResponse> getCities();

    Optional<City> findCityByName(String name);

}
