package ru.itis.pethome.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.pethome.dto.response.CityResponse;

import java.util.List;

@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:3000")
public interface CityApi {

    @GetMapping
    List<CityResponse> getCity();
}
