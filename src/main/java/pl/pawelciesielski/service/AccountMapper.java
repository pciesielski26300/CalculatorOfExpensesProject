package pl.pawelciesielski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pawelciesielski.api.dto.AccountRequest;
import pl.pawelciesielski.persistence.Account;
import pl.pawelciesielski.persistence.Role;
import pl.pawelciesielski.persistence.RoleRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account map(AccountRequest accountRequest) {
        String encodedPassword = bCryptPasswordEncoder.encode(accountRequest.getPassword());
        return Account
                .builder()
                .login(accountRequest.getLogin())
                .password(encodedPassword)
                .roles(Set.of(roleRepository.findByRoleName("USER")))
                .build();
    }
}
