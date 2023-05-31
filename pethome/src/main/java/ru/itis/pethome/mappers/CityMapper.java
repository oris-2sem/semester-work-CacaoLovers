package ru.itis.pethome.mappers;

import org.mapstruct.Mapper;
import ru.itis.pethome.dto.response.CityResponse;
import ru.itis.pethome.model.City;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<City, City, CityResponse>{
    CityResponse toResponse(City object);
}
