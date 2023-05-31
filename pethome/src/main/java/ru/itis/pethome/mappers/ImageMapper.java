package ru.itis.pethome.mappers;

import org.mapstruct.Mapper;
import ru.itis.pethome.dto.response.ImageResponse;
import ru.itis.pethome.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponse toResponse(Image image);
}
