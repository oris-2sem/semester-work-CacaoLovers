package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pethome.dao.CityDao;
import ru.itis.pethome.dao.DistrictDao;
import ru.itis.pethome.dto.request.DistrictRequest;
import ru.itis.pethome.dto.response.CityResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.mappers.CityMapper;
import ru.itis.pethome.mappers.DistrictMapper;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictDao districtDao;
    private final CityDao cityDao;
    private final CityMapper cityMapper;

    private final DistrictMapper districtMapper;

    @Override
    public District createDistrict(District district) {
        return null;
    }

    @Override
    public District updateDistrict(District district) {
        return null;
    }

    @Override
    public Optional<District> getDistrictByName(DistrictRequest district) {
        return Optional.ofNullable(districtDao.getDistrictByNameAndCity(district.getName(), district.getCity()));
    }

    @Override
    public List<DistrictResponse> getDistrictByCity(String city) {
        City cityResult = cityDao.findByName(city).orElseThrow();
        return districtMapper.toListResponse(districtDao.getDistrictByCity(cityResult));
    }

    @Override
    public Optional<District> resolutionDistrict(DistrictRequest districtRequest) {
        Optional<District> district = getDistrictByName(districtRequest);

        if (district.isEmpty()){
            Optional<City> city = cityDao.findByName(districtRequest.getCity());

            if (city.isEmpty()){
                city = Optional.of(cityDao.save(City.builder()
                                .posX(districtRequest.getPosX())
                                .posY(districtRequest.getPosY())
                                .name(districtRequest.getCity())
                                .build()));
            }

            district = Optional.of(districtDao.save(District.builder()
                            .city(city.get())
                            .posX(districtRequest.getPosX())
                            .posY(districtRequest.getPosY())
                            .name(districtRequest.getName())
                            .build()));
        }

        return district;
    }

    @Override
    public void deleteDistrict(District district) {

    }

    @Override
    public City createCity(City city) {
        return null;
    }

    @Override
    public City updateCity(City city) {
        return null;
    }

    @Override
    public City deleteCity(City city) {
        return null;
    }

    @Override
    public List<CityResponse> getCities() {
        return cityMapper.toListResponse(cityDao.findAll());
    }

    @Override
    public Optional<City> findCityByName(String name) {
        return cityDao.findByName(name);
    }
}
