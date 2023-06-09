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
public class VolunteerResponse {
    private AccountResponse account;
    private Integer rating;
    private Integer summaryFound;
    private Integer dayOfVolunteer;
}
