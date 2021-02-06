package pl.pawelciesielski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.AccountRequest;
import pl.pawelciesielski.persistence.Account;
import pl.pawelciesielski.persistence.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public void createAccount(final AccountRequest accountRequest) {
        Account account = accountMapper.map(accountRequest);
        accountRepository.save(account);
    }
}
