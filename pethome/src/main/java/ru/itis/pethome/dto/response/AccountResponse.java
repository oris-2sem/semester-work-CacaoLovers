package ru.itis.pethome.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.itis.pethome.model.City;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountResponse {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private City city;
    private String role;
    private String status;
    private long createdTime;
    private long updatedTime;
}
