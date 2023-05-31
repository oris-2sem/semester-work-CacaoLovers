package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dao.ConfirmAccountDao;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.HttpControllerException;
import ru.itis.pethome.exception.UserNotFoundException;
import ru.itis.pethome.exception.UsernameAlreadyTaken;
import ru.itis.pethome.mappers.AccountMapper;
import ru.itis.pethome.model.Account;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final AccountMapper accountMapper;
    private final ConfirmAccountDao confirmAccountDao;

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        return accountMapper.toResponse(accountDao.save(accountMapper.toEntity(accountRequest)));
    }

    @Override
    public AccountResponse confirmAccount(UUID uuid) {
        return null;
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(AccountRequest accountRequest) throws UserNotFoundException {
        Account account = accountDao.findById(accountRequest.getId())
                .orElseThrow(() -> new UserNotFoundException(accountRequest.getId()));

        account = accountMapper.toEntity(accountRequest);
        accountDao.save(account);

        return accountMapper.toResponse(account);
    }

    @Override
    @Transactional
    public void deleteAccount(UUID uuid) throws UserNotFoundException {
        Account account = accountDao.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

        accountDao.delete(account);
    }

    @Override
    public AccountResponse getAccountById(UUID uuid) {
        try {

            Account account = accountDao.findById(uuid)
                    .orElseThrow(() -> new UserNotFoundException(uuid));

            return accountMapper.toResponse(account);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public AccountResponse getAccountByUsername(String username) {
        return null;
    }

    @Override
    public List<AccountResponse> getAccountList() {
        return accountMapper.toListResponse(accountDao.findAll());
    }

    @Override
    public List<AccountResponse> getAccountPageableList(int page) {
        return null;
    }

    @Override
    public AccountResponse signUp(AccountRequest accountRequest) {
        try {

            if (accountDao.findByUsername(accountRequest.getUsername()).isPresent()) throw new UsernameAlreadyTaken("Пользователь c таким именем уже существует");

        } catch (HttpControllerException e) {
                throw new RuntimeException(e);
        }
        return accountMapper.toResponse(accountDao.save(accountMapper.toEntity(accountRequest)));
    }
}
