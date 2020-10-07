package pl.pawelciesielski.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.service.ExpenseService;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService service;

    //ma przyjac request http restowy i przeslac do servisu
    @PostMapping(path = "/api/expense")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void save(ExpenseRequest expenseRequest){
         service.save(expenseRequest);
    }
}
