package ru.itis.pethome.service;

import ru.itis.pethome.dto.request.CityRequest;
import ru.itis.pethome.dto.request.DistrictRequest;
import ru.itis.pethome.dto.response.CityResponse;
import ru.itis.pethome.dto.response.DistrictInfoResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistrictService {

    District createDistrict(District district);

    District updateDistrict(District district);

    List<DistrictResponse> getDistricts();

    Optional<District> getDistrictByName(DistrictRequest district);

    List<DistrictResponse> getDistrictByCity(String city);

    Optional<District> resolutionDistrict(DistrictRequest districtRequest);

    void deleteDistrict(District district);

    void deleteDistrictById(UUID id);

    CityResponse createCity(CityRequest city);

    City updateCity(City city);

    City deleteCity(City city);

    List<CityResponse> getCities();

    Optional<City> findCityByName(String name);

}
