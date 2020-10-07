package pl.pawelciesielski.api;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.service.ExpenseService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService service;


    @PostMapping(path = "/api/expense")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void save(@RequestBody ExpenseRequest expenseRequest){
         service.save(expenseRequest);
    }

    @GetMapping(path = "/api/expense")
    public ResponseEntity<ExpenseResponse> findById(@RequestParam long uuid){
        ExpenseResponse expense = service.findExpense(uuid);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }


}
