package ru.itis.pethome.service;

import ru.itis.pethome.dto.response.ImageResponse;
import ru.itis.pethome.model.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    ImageResponse addImage(Image image);
    ImageResponse getImageById(UUID id);
    ImageResponse updateImage(Image image);
}
