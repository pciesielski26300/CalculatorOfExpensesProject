package pl.pawelciesielski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.persistence.Account;
import pl.pawelciesielski.persistence.AccountRepository;
import pl.pawelciesielski.persistence.Role;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> optionalUser = accountRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            Account account = optionalUser.get();


            String[] roles = account.
                    getRoles()
                    .stream()
                    .map(Role::getRoleName)
                    .toArray(String[]::new);
            return User
                    .builder()
                    .username(account.getLogin())
                    .password(account.getPassword())
                    .roles(roles)
                    .build();
        } else {
            throw new UsernameNotFoundException("Username is not found");
        }
    }
}
