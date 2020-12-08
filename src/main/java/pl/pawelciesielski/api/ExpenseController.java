package pl.pawelciesielski.api;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.ExpensesResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.service.ExpenseService;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService service;


    @PostMapping(path = "/api/expense")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void save(@RequestBody ExpenseRequest expenseRequest) {
        service.save(expenseRequest);
    }

    @GetMapping(path = "/api/expense")
    public ResponseEntity<ExpenseResponse> findExpense(@RequestParam long id) {
        ExpenseResponse expense = service.findExpense(id);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/api/expense/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }


    @GetMapping(path = "/api/category")
    public ResponseEntity<ExpensesResponse> findAllExpensesByCategoryAndTotalValue(@RequestParam Category categoryOfExpense) {
        ExpensesResponse expenseResponse = service.getExpensesResponse(categoryOfExpense);
        return new ResponseEntity<>(expenseResponse, HttpStatus.ACCEPTED);
    }


    @GetMapping(path = "/api/date")
    public ResponseEntity<List<ExpenseResponse>> findByLocalDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {


        List<ExpenseResponse> expenseResponses = service.findByLocalDate(localDate);
        return new ResponseEntity<>(expenseResponses, HttpStatus.ACCEPTED);
    }

}
