package ru.itis.pethome.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.controller.api.CityApi;
import ru.itis.pethome.dto.response.CityResponse;
import ru.itis.pethome.service.DistrictService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityController implements CityApi {

    private final DistrictService districtService;

    @Override
    public List<CityResponse> getCity() {
        return districtService.getCities();
    }
}
