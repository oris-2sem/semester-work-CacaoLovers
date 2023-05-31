package ru.itis.pethome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.itis.pethome.model.District;
import ru.itis.pethome.model.Missing;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MissingRequest {
    private UUID id;
    private Double posX;
    private Double posY;
    private String description;
    private String name;
    private String kind;
    private String gender;

    private ImageRequest image;

    private DistrictRequest district;
    private AccountRequest owner;
    private Missing.Type type;
    private Missing.Status status;
}
