package pl.pawelciesielski.api;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.ExpensesResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.service.ExpenseMapper;
import pl.pawelciesielski.service.ExpenseService;

import java.time.OffsetDateTime;
import java.util.List;
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

//     @DeleteMapping(value = "/api/expense/{id}")
//     @ResponseStatus(code = HttpStatus.ACCEPTED)
//     public void delete(@PathVariable Long id){
//        service.deleteById(id);
//     }2020-10-20T00:00:00+02:00

    @GetMapping(path = "/api/Category")
    public ResponseEntity<List<ExpenseResponse>> findByCategoryOfExpense(@RequestParam Category categoryOfExpense){
        List<ExpenseResponse> expenseResponse = service.findByCategoryOfExpense(categoryOfExpense);
        return new ResponseEntity<>(expenseResponse, HttpStatus.ACCEPTED);
    }
    @GetMapping(path = "/api/ExpensesResponse")
    public ResponseEntity<ExpensesResponse> getExpensesResponse(@RequestParam Category categoryOfExpense){
        ExpensesResponse expenseResponse = service.getExpensesResponse(categoryOfExpense);
        return new ResponseEntity<>(expenseResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/api/date")
    public ResponseEntity<List<ExpenseResponse>> findByOffsetDateTime(@RequestParam OffsetDateTime offsetDateTime){
        List<ExpenseResponse> expenseResponses = service.findByOffsetDateTime(offsetDateTime);
        return new ResponseEntity<>(expenseResponses, HttpStatus.ACCEPTED);
    }


}
