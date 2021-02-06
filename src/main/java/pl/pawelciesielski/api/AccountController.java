package pl.pawelciesielski.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pawelciesielski.api.dto.AccountRequest;
import pl.pawelciesielski.service.AccountService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createAccount(@RequestBody AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
    }
}
