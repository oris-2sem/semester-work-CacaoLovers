package ru.itis.pethome.mappers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.model.Account;

@Component
@AllArgsConstructor
public class AccountMapper implements EntityMapper<Account, AccountRequest, AccountResponse>{

    private final PasswordEncoder passwordEncoder;

    @Override
    public Account toEntity(AccountRequest accountRequest) {
        return Account.builder()
                .email(accountRequest.getEmail())
                .username(accountRequest.getUsername())
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .hashPassword(passwordEncoder.encode(accountRequest.getPassword()))
                .city(accountRequest.getCity())
                .role(Account.Role.valueOf("USER"))
                .status(Account.Status.valueOf("UNCONFIRMED"))
                .build();
    }

    @Override
    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .username(account.getUsername())
                .id(account.getId())
                .city(account.getCity())
                .role(account.getRole().name())
                .status(account.getStatus().name())
                .createdTime(account.getCreatedTime().toEpochMilli())
                .updatedTime(account.getUpdatedTime().toEpochMilli())
                .build();
    }
}
