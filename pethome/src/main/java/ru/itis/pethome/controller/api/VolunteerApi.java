package ru.itis.pethome.controller.api;


import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.request.VolunteerRequest;
import ru.itis.pethome.dto.response.DistrictInfoResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.dto.response.VolunteerResponse;

import java.util.List;
import java.util.UUID;

@RequestMapping("/volunteer")
@CrossOrigin(origins = "http://localhost:3000")
public interface VolunteerApi {

    @GetMapping("/district/{districtId}")
    List<VolunteerResponse> getVolunteerByDistrict(@PathVariable UUID districtId);

    @GetMapping("/{accountId}")
    List<DistrictInfoResponse> getDistrictInfoByAccount(@PathVariable UUID accountId);

    @PostMapping
    VolunteerResponse addVolunteerDistrict(@RequestBody VolunteerRequest volunteerRequest);

    @GetMapping("/unselect/{accountId}")
    List<DistrictResponse> getDistrictByAccountWithoutSelectDistrict(@PathVariable UUID accountId);

    @GetMapping("/select/{accountId}")
    List<DistrictResponse> getDistrictByAccountWithSelectDistrict(@PathVariable UUID accountId);

    @DeleteMapping
    VolunteerResponse deleteVolunteerDistrict(@RequestBody VolunteerRequest volunteerRequest);
}
