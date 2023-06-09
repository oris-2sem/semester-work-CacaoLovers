package ru.itis.pethome.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.controller.api.VolunteerApi;
import ru.itis.pethome.dto.request.VolunteerRequest;
import ru.itis.pethome.dto.response.DistrictInfoResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.dto.response.VolunteerResponse;
import ru.itis.pethome.model.Volunteer;
import ru.itis.pethome.service.VolunteerService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class VolunteerController implements VolunteerApi {

    private final VolunteerService volunteerService;

    @Override
    public List<VolunteerResponse> getVolunteerByDistrict(UUID districtId) {
        return volunteerService.getVolunteerByDistrict(districtId);
    }

    @Override
    public List<DistrictInfoResponse> getDistrictInfoByAccount(UUID accountId) {
        return volunteerService.getDistrictInfoByAccount(accountId);
    }

    @Override
    public VolunteerResponse addVolunteerDistrict(VolunteerRequest volunteerRequest) {
        return volunteerService.addDistrict(volunteerRequest.getAccountId(), volunteerRequest.getDistrictId());
    }

    @Override
    public List<DistrictResponse> getDistrictByAccountWithoutSelectDistrict( UUID accountId) {
        return volunteerService.getDistrictByAccountWithoutSelectDistrict(accountId);
    }

    @Override
    public List<DistrictResponse> getDistrictByAccountWithSelectDistrict(UUID accountId) {
        return volunteerService.getDistrictByAccountWithSelectDistrict(accountId);
    }

    @Override
    public VolunteerResponse deleteVolunteerDistrict(VolunteerRequest volunteerRequest) {
        return volunteerService.deleteDistrict(volunteerRequest.getAccountId(), volunteerRequest.getDistrictId());
    }
}
