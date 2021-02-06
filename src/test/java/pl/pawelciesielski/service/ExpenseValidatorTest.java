package pl.pawelciesielski.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pawelciesielski.persistence.Category;
import pl.pawelciesielski.persistence.Expense;

import java.time.LocalDate;

public class ExpenseValidatorTest {
    private ExpenseValidator validator;

    @BeforeEach
    public void setup() {
      validator = new ExpenseValidator();
    }

    @Test
    public void category_isNull_throwsException() {
        Expense expense = new Expense(5L, null, 1000.0, "Koła", LocalDate.of(2020, 12, 20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isNull(expense));

    }

    @Test
    public void value_isNull_throwsException(){
        Expense expense = new Expense(5L, Category.CAR, 0, "Koła", LocalDate.of(2020, 12, 20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isNull(expense));
    }

    @Test
    public void description_isNull_throwsException(){
        Expense expense = new Expense(5L, Category.CAR, 12332, null, LocalDate.of(2020, 12, 20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isNull(expense));
    }
    @Test
    public void date_isNull_throwsException(){
        Expense expense = new Expense(5L, Category.CAR, 12332, "null", null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isNull(expense));
    }

}
