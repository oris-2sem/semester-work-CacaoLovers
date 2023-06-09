package ru.itis.pethome.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.itis.pethome.model.City;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class DistrictResponse {

    UUID id;
    String name;
    CityResponse city;
    Double posX;
    Double posY;
}
