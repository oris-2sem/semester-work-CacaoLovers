package ru.itis.pethome.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {


    AccountResponse createAccount(AccountRequest accountRequest);

    AccountResponse confirmAccount(UUID uuid);

    AccountResponse updateAccount(AccountRequest accountRequest) throws UserNotFoundException;

    void deleteAccount(UUID uuid) throws UserNotFoundException;

    AccountResponse getAccountById( UUID uuid);


    AccountResponse getAccountByUsername( String username);

    List<AccountResponse> getAccountList();

    List<AccountResponse> getAccountPageableList( int page);

    AccountResponse signUp(AccountRequest accountRequest);
}
