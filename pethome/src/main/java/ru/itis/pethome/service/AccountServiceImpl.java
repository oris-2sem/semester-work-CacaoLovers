package ru.itis.pethome.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dao.ConfirmAccountDao;
import ru.itis.pethome.dto.request.AccountRequest;
import ru.itis.pethome.dto.request.SignUpRequest;
import ru.itis.pethome.dto.response.AccountResponse;
import ru.itis.pethome.exception.CityNotFoundException;
import ru.itis.pethome.exception.HttpControllerException;
import ru.itis.pethome.exception.UserNotFoundException;
import ru.itis.pethome.exception.UsernameAlreadyTaken;
import ru.itis.pethome.mappers.AccountMapper;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.City;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final AccountMapper accountMapper;
    private final DistrictService districtService;


    @Override
    public AccountResponse confirmAccount(UUID uuid) {
        return null;
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(AccountRequest accountRequest) throws UserNotFoundException {
        Account account = accountDao.findById(accountRequest.getId())
                .orElseThrow(() -> new UserNotFoundException(accountRequest.getId()));

        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        if (accountRequest.getEmail() != null){
            account.setEmail(accountRequest.getEmail());
        }
        account.setEmail(accountRequest.getEmail());
        if (accountRequest.getCity() != null) {
            account.setCity(districtService.findCityByName(accountRequest.getCity().getName()).orElseThrow(() -> new CityNotFoundException(accountRequest.getCity().getName())));
        }

        return accountMapper.toResponse(accountDao.save(account));
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
        return accountMapper.toResponse(accountDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username)));
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
    public AccountResponse signUp(SignUpRequest signUpRequest) {
        if (accountDao.findByUsername(signUpRequest.getUsername()).isPresent()) throw new UsernameAlreadyTaken("Пользователь c таким именем уже существует");
        Account account = accountMapper.toAccountFromSignUp(signUpRequest);
        City city = districtService.findCityByName("").get();
        account.setRole(Account.Role.USER);
        account.setStatus(Account.Status.CONFIRMED);
        account.setCity(city);
        return accountMapper.toResponse(accountDao.save(account));
    }
}
