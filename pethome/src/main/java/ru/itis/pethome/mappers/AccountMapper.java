package ru.itis.pethome.mappers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.request.SignUpRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.CityNotFoundException;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.service.DistrictService;

@Component
@AllArgsConstructor
public class AccountMapper implements EntityMapper<Account, AccountRequest, AccountResponse>{

    private final PasswordEncoder passwordEncoder;
    private final DistrictService districtService;
    private final DistrictMapper districtMapper;
    private final CityMapper cityMapper;

    @Override
    public Account toEntity(AccountRequest accountRequest) {
        return Account.builder()
                .email(accountRequest.getEmail())
                .username(accountRequest.getUsername())
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .hashPassword(passwordEncoder.encode(accountRequest.getPassword()))
                .city(districtService.findCityByName(accountRequest.getCity().getName()).orElseThrow(() -> new CityNotFoundException(accountRequest.getCity().getName())))
                .image(accountRequest.getImage())
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
                .city(cityMapper.toResponse(account.getCity()))
                .role(account.getRole().name())
                .status(account.getStatus().name())
                .image(account.getImage())
                .createdTime(account.getCreatedTime().toEpochMilli())
                .updatedTime(account.getUpdatedTime().toEpochMilli())
                .districts(account.getDistricts() == null ? null : districtMapper.toListResponse(account.getDistricts()))
                .build();
    }

    public Account toAccountFromSignUp(SignUpRequest signUpRequest){
        return Account.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .hashPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

    }
}
