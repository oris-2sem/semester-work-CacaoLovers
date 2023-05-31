package ru.itis.pethome.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.response.DistrictResponse;

import java.util.List;

@RequestMapping("/district")
@CrossOrigin(origins = "http://localhost:3000")
public interface DistrictApi {

    @GetMapping
    List<DistrictResponse> getDistrictsByCity(@RequestParam String city);

}
