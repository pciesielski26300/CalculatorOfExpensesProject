package pl.pawelciesielski.service;


import org.springframework.stereotype.Component;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistance.Expense;

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
    public ExpenseResponse map(Expense expense){
        return ExpenseResponse
                .builder()
                .categoryOfExpense(expense.getCategoryOfExpense())
                .description(expense.getDescription())
                .value(expense.getValue())
                .offsetDateTime(expense.getOffsetDateTime())
                .build();

    }

}
