package ru.itis.pethome.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.Volunteer;

import java.util.Optional;
import java.util.UUID;

public interface VolunteerDao extends JpaRepository<Volunteer, UUID> {

    Optional<Volunteer> findVolunteerByAccount(Account account);

}
