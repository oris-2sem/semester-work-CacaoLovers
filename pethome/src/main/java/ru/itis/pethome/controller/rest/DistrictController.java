package ru.itis.pethome.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.controller.api.DistrictApi;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.service.DistrictService;

import java.util.List;

@RestController
@AllArgsConstructor
public class DistrictController implements DistrictApi {

    private final DistrictService districtService;

    @Override
    public List<DistrictResponse> getDistrictsByCity(String city) {
        return districtService.getDistrictByCity(city);
    }
}
