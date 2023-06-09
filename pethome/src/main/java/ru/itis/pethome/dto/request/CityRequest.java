package ru.itis.pethome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CityRequest {
    private UUID id;
    private String name;
    private String posX;
    private String posY;
}
