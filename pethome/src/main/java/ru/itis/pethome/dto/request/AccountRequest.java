package ru.itis.pethome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.itis.pethome.model.City;

import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountRequest {

    private UUID id;

    @Size(min = 3, max = 10, message = "Ник должен состоять не менее из 3 символов")
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @Size(min = 3, max = 10, message = "Пароль должен состоять не менее из 3 символов")
    private String password;
    private City city;
    private String role;
    private String status;
}
