package ru.itis.pethome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.pethome.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountDao extends JpaRepository<Account, UUID> {

    Optional<Account> findByUsername(String username);
    Optional<Account> findAccountByEmail(String email);
}
