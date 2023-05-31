package ru.itis.pethome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CityResponse {
    private UUID id;
    private String name;
    private Double posX;
    private Double posY;
}
