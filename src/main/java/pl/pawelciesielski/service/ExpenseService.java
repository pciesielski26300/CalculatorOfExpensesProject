package pl.pawelciesielski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.ExpensesResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.persistance.ExpenseRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository expenseRepository;
    private final ExpenseValidator expenseValidator;





    public void save(Expense expense) {
        expenseValidator.isNull(expense);
        expenseRepository.save(expense);
    }

    public void save(ExpenseRequest expenseRequest) {
        Expense expense = mapper.map(expenseRequest);
        expense.setLocalDate(LocalDate.now());
        save(expense);
    }


    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow();
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

    public ExpensesResponse getExpensesResponse(Category categoryOfExpense) {
        List<ExpenseResponse> byCategoryOfExpense = findByCategoryOfExpense(categoryOfExpense);
        return ExpensesResponse
                .builder()
                .expenses(byCategoryOfExpense)
                .build();
    }

    public List<ExpenseResponse> findByLocalDate(LocalDate localDate) {
        List<Expense> expenses = expenseRepository.findByLocalDate(localDate);
        return mapper.map(expenses);
    }


}
