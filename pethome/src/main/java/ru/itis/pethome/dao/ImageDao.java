package ru.itis.pethome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.pethome.model.Image;

import java.util.UUID;

public interface ImageDao extends JpaRepository<Image, UUID> {
}
