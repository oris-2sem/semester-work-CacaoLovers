package ru.itis.pethome.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:3000")
public interface AccountApi {

    @PostMapping
    AccountResponse createAccount(@RequestBody AccountRequest accountRequest);

    @RequestMapping("/sign")
    @PostMapping
    AccountResponse signUpAccount(@RequestBody AccountRequest accountRequest);

    @GetMapping("/confirm/{uuid}")
    AccountResponse confirmAccount(@PathVariable UUID uuid);

    @PutMapping
    AccountResponse updateAccount(@RequestBody AccountRequest accountRequest) throws UserNotFoundException;

    @DeleteMapping("/{uuid}")
    void deleteAccount(@PathVariable UUID uuid) throws UserNotFoundException;

    @GetMapping("/{uuid}")
    AccountResponse getAccountById(@PathVariable UUID uuid);

    @GetMapping
    AccountResponse getAccountByUsername(@RequestParam String username);

    @GetMapping("/list")
    List<AccountResponse> getAccountList();

    @GetMapping("/list/{page}")
    List<AccountResponse> getAccountPageableList(@PathVariable int page);

}
