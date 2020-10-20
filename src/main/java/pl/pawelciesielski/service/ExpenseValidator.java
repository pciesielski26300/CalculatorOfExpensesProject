package pl.pawelciesielski.service;

import pl.pawelciesielski.persistance.Expense;

import java.util.Objects;

public class ExpenseValidator {
    public void isNull(Expense expense){
         if (Objects.isNull(expense.getCategoryOfExpense())){
             throw new IllegalArgumentException("Invalid category!");
         }
         if (Objects.isNull(expense.getDescription())){
             throw new IllegalArgumentException("Invalid description!");
         }
         if (Objects.isNull(expense.getOffsetDateTime())){
             throw new IllegalArgumentException("Invalid date!");
         }
         if (expense.getValue() == 0){
             throw new IllegalArgumentException("Invalid value!");
         }
//         if (Objects.isNull(expense.getId())){
//             throw new IllegalArgumentException("Invalid ID!");
//         }
    }
}
