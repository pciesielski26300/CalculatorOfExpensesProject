package pl.pawelciesielski;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.persistance.ExpenseRepository;
import pl.pawelciesielski.service.ExpenseMapper;
import pl.pawelciesielski.service.ExpenseService;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.mock;

public class ExpenseServiceTest {
    private ExpenseRepository repository;
    private ExpenseService service;

    @BeforeEach
    public void setup(){
        repository = mock(ExpenseRepository.class);
        ExpenseMapper mapper = mock(ExpenseMapper.class);
        service = new ExpenseService(mapper, repository);
    }

    @Test
    public void save_allParamsOk_savedCorrectly(){
        //given
        Expense expense = new Expense(5L, Category.CAR, 1000.0, "Koła", OffsetDateTime.now());

        //when/then
        service.save(expense);
    }

    @Test
    public void save_nullCategory_throwsException(){
        //given
        Expense expense = new Expense(5L, null, 1000.0, "Koła", OffsetDateTime.now());

        //when/then
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_totalSumIsZero_throwsException(){
        //given
        Expense expense = new Expense(5L, Category.CAR, 0, "Koła", OffsetDateTime.now());

        //when/then
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_descriptionIsNull_throwsException(){
        Expense expense = new Expense(5L, Category.CAR, 5, null, OffsetDateTime.now());

        //when/then
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_dateIsNull_throwsException(){
        //given
        Expense expense = new Expense(5L, Category.CAR, 5, "Koła", null);

        //when/then
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }
}
