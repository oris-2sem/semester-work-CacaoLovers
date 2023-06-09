package ru.itis.pethome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpRequest {
    @Size(min=4, message = "Никнейм должен содержать минимум 4 символа")
    private String username;

    private String email;
    @Size(min=4, message = "Пароль должен содержать минимум 4 символа")
    private String password;
}
