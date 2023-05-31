package ru.itis.pethome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.pethome.model.ConfirmAccount;

import java.util.UUID;

public interface ConfirmAccountDao extends JpaRepository<ConfirmAccount, UUID> {
}
