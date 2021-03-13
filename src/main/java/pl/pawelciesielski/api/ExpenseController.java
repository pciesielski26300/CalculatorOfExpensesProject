package pl.pawelciesielski.api;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.MultipleExpensesResponse;
import pl.pawelciesielski.persistence.Category;
import pl.pawelciesielski.service.ExpenseService;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExpenseController {
    private final ExpenseService service;


    @PostMapping(path = "/expense")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void save(@RequestBody ExpenseRequest expenseRequest, @AuthenticationPrincipal User user) {

        service.save(expenseRequest, user.getUsername());
    }

    @GetMapping(path = "/expense")
    public ResponseEntity<ExpenseResponse> findExpense(@RequestParam long id) {
        ExpenseResponse expense = service.findExpense(id);
        return new ResponseEntity<>(expense, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/expense/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }


    @GetMapping(path = "/category")
    public ResponseEntity<MultipleExpensesResponse> findAllExpensesByCategoryAndTotalValue(@RequestParam Category categoryOfExpense) {
        MultipleExpensesResponse expenseResponse = service.getExpensesResponse(categoryOfExpense);
        return new ResponseEntity<>(expenseResponse, HttpStatus.ACCEPTED);
    }


    @GetMapping(path = "/date")
    public ResponseEntity<List<ExpenseResponse>> findByLocalDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {


        List<ExpenseResponse> expenseResponses = service.findByLocalDate(localDate);
        return new ResponseEntity<>(expenseResponses, HttpStatus.ACCEPTED);
    }

}
