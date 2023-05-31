package ru.itis.pethome.service;

import ru.itis.pethome.dto.response.VolunteerResponse;

import java.util.UUID;


public interface VolunteerService {
    VolunteerResponse addDistrict(UUID accountId, UUID districtId);
    VolunteerResponse deleteDistrict(UUID accountId, UUID districtId);
    VolunteerResponse getVolunteer(UUID accountId);
}
