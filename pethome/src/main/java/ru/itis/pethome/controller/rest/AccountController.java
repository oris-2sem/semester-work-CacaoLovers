package ru.itis.pethome.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.controller.api.AccountApi;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.request.SignUpRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.UserNotFoundException;
import ru.itis.pethome.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService accountService;


    @Override
    public AccountResponse signUpAccount(SignUpRequest signUpRequest) {
        return accountService.signUp(signUpRequest);
    }

    @Override
    public AccountResponse confirmAccount(UUID uuid) {
        return accountService.confirmAccount(uuid);
    }


    @Override
    public AccountResponse updateAccount(AccountRequest accountRequest) throws UserNotFoundException {
        return accountService.updateAccount(accountRequest);
    }

    @Override
    public void deleteAccount(UUID uuid) throws UserNotFoundException {
        accountService.deleteAccount(uuid);
    }

    @Override
    public AccountResponse getAccountById(UUID uuid) {
        return accountService.getAccountById(uuid);
    }

    @Override
    public AccountResponse getAccountByUsername(String username) {
        return null;
    }

    @Override
    public List<AccountResponse> getAccountList() {
        return accountService.getAccountList();
    }

    @Override
    public List<AccountResponse> getAccountPageableList(int page) {
        return null;
    }
}
