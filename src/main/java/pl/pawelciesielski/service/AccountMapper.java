package pl.pawelciesielski.service;

import org.springframework.stereotype.Component;
import pl.pawelciesielski.api.dto.AccountRequest;
import pl.pawelciesielski.persistence.Account;

@Component
public class AccountMapper {
    public Account map(AccountRequest accountRequest) {
        return Account
                .builder()
                .login(accountRequest.getLogin())
                .password(accountRequest.getPassword())
                .build();
    }
}
