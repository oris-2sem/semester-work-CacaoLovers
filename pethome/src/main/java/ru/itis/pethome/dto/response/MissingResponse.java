package ru.itis.pethome.dto.response;

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
public class MissingResponse {

    private UUID id;
    private Double posX;
    private Double posY;
    private String description;
    private String name;
    private String kind;
    private String gender;
    private String imagePath;

    private District district;
    private AccountResponse owner;
    private Missing.Type type;
    private Missing.Status status;

}
