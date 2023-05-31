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
public class ImageResponse {
    private UUID id;
    private String oldName;
    private String type;
    private Integer weight;
    private String path;
}
