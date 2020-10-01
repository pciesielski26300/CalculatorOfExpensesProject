package pl.pawelciesielski.service;


import org.springframework.stereotype.Component;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.persistance.Expense;

@Component
public class ExpenseMapper {
    public Expense map(ExpenseRequest expenseRequest) {
        return Expense
                .builder()
                .categoryOfExpense(expenseRequest.getCategoryOfExpense())
                .totalSumOfExpenses(expenseRequest.getTotalSumOfExpenses())
                .description(expenseRequest.getDescription())
                .build();
    }
}
