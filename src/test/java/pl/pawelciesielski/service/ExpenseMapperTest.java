package pl.pawelciesielski.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistence.Account;
import pl.pawelciesielski.persistence.Category;
import pl.pawelciesielski.persistence.Expense;

import java.time.LocalDate;
import java.util.List;

public class ExpenseMapperTest {
    private ExpenseMapper mapper;
    private Account account;

    @BeforeEach
    public void setup() {
        mapper = new ExpenseMapper();

    }

    @Test
    public void expense_map_mappedCorrectly() {
        ExpenseRequest expenseRequest = new ExpenseRequest(Category.CAR, 500, "test");
        Expense expected = new Expense(null, Category.CAR, 500, "test", null, account);
        Expense actual = mapper.map(expenseRequest);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void expenseResponse_map_mappedCorrectly() {
        Expense expense = new Expense(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20), account);
        ExpenseResponse expected = new ExpenseResponse(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20));
        ExpenseResponse actual = mapper.map(expense);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void listOfExpenseResponse_map_mappedCorrectly() {
        ExpenseResponse expenseResponse = new ExpenseResponse(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20));
        ExpenseResponse expenseResponse2 = new ExpenseResponse(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20));
        List<ExpenseResponse> expectedList = List.of(expenseResponse, expenseResponse2);
        Expense expense = new Expense(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20), account);
        Expense expense2 = new Expense(null, Category.CAR, 500, "test", LocalDate.of(2000, 12, 20), account);
        List<Expense> mappedList = List.of(expense, expense2);
        List<ExpenseResponse> actualList = mapper.map(mappedList);

        Assertions.assertEquals(expectedList, actualList);
    }
}
