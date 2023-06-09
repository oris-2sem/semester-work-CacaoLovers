package ru.itis.pethome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DistrictInfoResponse {
    private DistrictResponse district;
    private Integer missingAll;
    private Integer missingToday;
    private Integer activeVolunteer;
    private List<MissingResponse> missingList;
}
