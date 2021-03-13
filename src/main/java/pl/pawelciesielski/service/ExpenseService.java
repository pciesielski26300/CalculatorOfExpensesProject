package pl.pawelciesielski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.MultipleExpensesResponse;
import pl.pawelciesielski.persistence.Account;
import pl.pawelciesielski.persistence.Category;
import pl.pawelciesielski.persistence.Expense;
import pl.pawelciesielski.persistence.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository expenseRepository;
    private final ExpenseValidator expenseValidator;
    private final AccountService accountService;


    public void save(Expense expense) {
        expenseValidator.isNull(expense);
        expenseRepository.save(expense);
    }

    public void save(ExpenseRequest expenseRequest, String login) {
        Account account = accountService.findByLogin(login);
        Expense expense = mapper.map(expenseRequest);
        expense.setLocalDate(LocalDate.now());
        save(expense);
    }


    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Expense with that id doesn't exist!"));
    }

    public ExpenseResponse findExpense(Long id) {
        final Expense expense = findById(id);
        return mapper.map(expense);
    }

    public void deleteById(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new NoSuchElementException("Expense with that ID does not exist");
        }
        expenseRepository.deleteById(id);
    }

    public List<ExpenseResponse> findByCategoryOfExpense(Category categoryOfExpense) {
        List<Expense> byCategoryOfExpense = expenseRepository.findByCategoryOfExpense(categoryOfExpense);
        return mapper.map(byCategoryOfExpense);
    }

    public MultipleExpensesResponse getExpensesResponse(Category categoryOfExpense) {
        List<ExpenseResponse> byCategoryOfExpense = findByCategoryOfExpense(categoryOfExpense);
        return MultipleExpensesResponse
                .builder()
                .expenses(byCategoryOfExpense)
                .build();
    }

    public List<ExpenseResponse> findByLocalDate(LocalDate localDate) {
        List<Expense> expenses = expenseRepository.findByLocalDate(localDate);
        return mapper.map(expenses);
    }


}
