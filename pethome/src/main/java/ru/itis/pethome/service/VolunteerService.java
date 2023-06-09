package ru.itis.pethome.service;

import ru.itis.pethome.dto.response.DistrictInfoResponse;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.dto.response.VolunteerResponse;

import java.util.List;
import java.util.UUID;


public interface VolunteerService {
    VolunteerResponse addDistrict(UUID accountId, UUID districtId);
    VolunteerResponse deleteDistrict(UUID accountId, UUID districtId);
    VolunteerResponse getVolunteer(UUID accountId);

    List<DistrictResponse> getDistrictByAccountWithoutSelectDistrict(UUID accountId);

    List<DistrictResponse> getDistrictByAccountWithSelectDistrict(UUID accountId);

    List<DistrictInfoResponse> getDistrictInfoByAccount(UUID accountId);


    List<VolunteerResponse> getVolunteerByDistrict(UUID districtId);
}
