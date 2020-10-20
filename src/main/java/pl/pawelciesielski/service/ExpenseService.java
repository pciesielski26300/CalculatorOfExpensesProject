package pl.pawelciesielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.ExpensesResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.persistance.ExpenseRepository;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository expenseRepository;


    @Autowired
    public ExpenseService(ExpenseMapper mapper, ExpenseRepository expenseRepository) {
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;

    }

    public void save(Expense expense) {
        ExpenseValidator expenseValidator = new ExpenseValidator();
        expenseValidator.isNull(expense);
        expenseRepository.save(expense);
    }

    public void save(ExpenseRequest expenseRequest) {
        Expense expense = mapper.map(expenseRequest);
        expense.setOffsetDateTime(OffsetDateTime.now());
        save(expense);
    }


    public Expense findById(Long uuid) {
        return expenseRepository.findById(uuid).orElseThrow();
    }

    public ExpenseResponse findExpense(Long uuid) {
        final Expense expense = findById(uuid);
        return mapper.map(expense);
    }

    public void deleteById(Long uuid) {
        expenseRepository.deleteById(uuid);
    }
    // sum total val of category, czy musze robic jakies query?

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

    public List<ExpenseResponse> findByOffsetDateTime(OffsetDateTime offsetDateTime) {
        List<Expense> expenses = expenseRepository.findByOffsetDateTime(offsetDateTime);
        return mapper.map(expenses);
    }


}
