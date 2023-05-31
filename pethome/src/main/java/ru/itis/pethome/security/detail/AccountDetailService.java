package ru.itis.pethome.security.detail;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dao.AccountDao;

@Component
@AllArgsConstructor
public class AccountDetailService implements UserDetailsService {

    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AccountDetail(accountDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
