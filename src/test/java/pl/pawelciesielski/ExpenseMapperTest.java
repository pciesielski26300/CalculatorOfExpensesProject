package pl.pawelciesielski;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.service.ExpenseMapper;

import java.time.LocalDate;

public class ExpenseMapperTest {
    private ExpenseMapper mapper;


    @BeforeEach
    public void setup() {
        mapper = new ExpenseMapper();

    }

    @Test
    public void expense_map_mappedCorrectly() {
        ExpenseRequest expenseRequest = new ExpenseRequest(null, Category.CAR, 500, "test");
        Expense expected = new Expense(null, Category.CAR, 500, "test", null);
        Expense actual = mapper.map(expenseRequest);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void expenseResponse_map_mappedCorrectly(){
        Expense expense = new Expense(null, Category.CAR, 500, "test", LocalDate.of(2000,12,20));
        ExpenseResponse expected = new ExpenseResponse(null, Category.CAR, 500, "test", LocalDate.of(2000,12,20));
        ExpenseResponse actual = mapper.map(expense);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void listOfExpenseResponse_map_mappedCorrectly(){

    }
}
