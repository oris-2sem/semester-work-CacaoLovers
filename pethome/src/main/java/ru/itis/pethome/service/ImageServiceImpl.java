package ru.itis.pethome.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pethome.dao.ImageDao;
import ru.itis.pethome.dto.response.ImageResponse;
import ru.itis.pethome.mappers.ImageMapper;
import ru.itis.pethome.model.Image;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageDao;
    private final ImageMapper imageMapper;

    @Override
    public ImageResponse addImage(Image image) {
        Image saveImage = imageDao.save(image);
        saveImage.setPath(saveImage.getId() + "." + saveImage.getType());
        imageDao.save(image);
        return imageMapper.toResponse(saveImage);
    }

    @Override
    public ImageResponse getImageById(UUID uuid) {
        return imageMapper.toResponse(imageDao.getReferenceById(uuid));
    }

    @Override
    @Transactional
    public ImageResponse updateImage(Image image) {
        return imageMapper.toResponse(imageDao.save(image));
    }
}
