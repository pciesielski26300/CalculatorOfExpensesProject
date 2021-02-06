package pl.pawelciesielski.service;


import org.springframework.stereotype.Component;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistence.Expense;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseMapper {
    public Expense map(ExpenseRequest expenseRequest) {
        return Expense
                .builder()
                .categoryOfExpense(expenseRequest.getCategoryOfExpense())
                .value(expenseRequest.getValue())
                .description(expenseRequest.getDescription())
                .build();
    }

    public ExpenseResponse map(Expense expense) {
        return ExpenseResponse
                .builder()
                .categoryOfExpense(expense.getCategoryOfExpense())
                .description(expense.getDescription())
                .value(expense.getValue())
                .localDate(expense.getLocalDate())
                .build();
    }


    public List<ExpenseResponse> map(List<Expense> byCategoryOfExpense) {
        return byCategoryOfExpense
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

    }
}
