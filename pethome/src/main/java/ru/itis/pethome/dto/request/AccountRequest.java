package ru.itis.pethome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.model.City;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountRequest {

    private UUID id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    private String password;
    private CityRequest city;
    private String role;
    private String status;

    private List<DistrictResponse> districts;
    private String image;
}
