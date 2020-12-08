package pl.pawelciesielski.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.persistance.ExpenseRepository;
import pl.pawelciesielski.service.ExpenseMapper;
import pl.pawelciesielski.service.ExpenseService;
import pl.pawelciesielski.service.ExpenseValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ExpenseServiceTest {
    private ExpenseRepository repository;
    private ExpenseService service;
    private ExpenseMapper mapper;
    private ExpenseValidator validator;


    @BeforeEach
    public void setup() {
        repository = mock(ExpenseRepository.class);
        mapper = mock(ExpenseMapper.class);
        validator = mock(ExpenseValidator.class);
        service = new ExpenseService(mapper, repository, validator);
    }

    @Test
    public void save_allParamsOk_savedCorrectly() {


        Expense expense = new Expense(5L, Category.CAR, 1000.0, "Koła", LocalDate.of(2020, 12, 20));

        service.save(expense);
        verify(repository, times(1)).save(expense);
    }

    @Test
    public void save_nullCategory_throwsException() {

        Expense expense = new Expense(5L, null, 1000.0, "Koła", LocalDate.of(2020, 12, 20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_totalSumIsZero_throwsException() {

        Expense expense = new Expense(5L, Category.CAR, 0, "Koła", LocalDate.of(2020, 12, 20));


        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_descriptionIsNull_throwsException() {
        Expense expense = new Expense(5L, Category.CAR, 5, null, LocalDate.of(2020, 12, 20));


        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void save_dateIsNull_throwsException() {

        Expense expense = new Expense(5L, Category.CAR, 5, "Koła", null);


        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(expense));
    }

    @Test
    public void findById_correctId_foundExpense() {

        Expense expense = new Expense(5L, Category.CAR, 5, "Koła", LocalDate.of(2020, 12, 20));

        when(repository.findById(5L)).thenReturn(Optional.of(expense));

        Expense actualExpense = service.findById(5L);

        Assertions.assertEquals(expense, actualExpense);

    }

    @Test
    public void findById_incorrectId_throwsException() {
        long id = 5L;
        Expense expense = new Expense(id, Category.CAR, 5, "Kasztan", LocalDate.of(2000,12,20));
        when(repository.findById(id)).thenThrow(new NoSuchElementException());


        Assertions.assertThrows(NoSuchElementException.class, () -> service.findById(id));
    }

    @Test
    public void deleteById_correctId_deletedCorrectly() {
        long id = 5L;
        when(repository.existsById(id)).thenReturn(true);

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deleteById_incorrectId_throwsException() {
        long id = 5L;

        when(repository.existsById(id)).thenReturn(false);


        Assertions.assertThrows(NoSuchElementException.class, () -> service.deleteById(id));

    }

    @Test
    public void findByCategoryOfExpense_correctCategory_foundExpenses() {
        Category other = Category.OTHER;
        Expense expense = new Expense(52L, other, 523, "Koła", LocalDate.of(2020, 12, 20));
        Expense expense2 = new Expense(52L, other, 523, "Koła", LocalDate.of(2020, 12, 20));
        List<Expense> expenses = List.of(expense, expense2);
        ExpenseResponse expenseResponse = new ExpenseResponse(52L, other, 523, "Koła", LocalDate.of(2000, 12, 20));
        ExpenseResponse expenseResponse2 = new ExpenseResponse(52L, other, 523, "Koła", LocalDate.of(2000, 12, 20));
        List<ExpenseResponse> expenseResponses = List.of(expenseResponse, expenseResponse2);

        when(repository.findByCategoryOfExpense(other)).thenReturn(expenses);
        when(mapper.map(expenses)).thenReturn(expenseResponses);

        List<ExpenseResponse> actualListOfExpensesResponses = service.findByCategoryOfExpense(other);

        Assertions.assertEquals(expenseResponses, actualListOfExpensesResponses);
    }

    @Test
    public void findByLocalDate_correctLocalDate_foundExpenses() {
        LocalDate localDate = LocalDate.of(2020, 12, 20);
        Expense expense = new Expense(52L, Category.CAR, 523, "Koła", localDate);
        Expense expense2 = new Expense(52L, Category.CAR, 523, "Koła", localDate);
        List<Expense> expenses = List.of(expense, expense2);
        ExpenseResponse expenseResponse = new ExpenseResponse(52L, Category.CAR, 523, "Koła", localDate);
        ExpenseResponse expenseResponse2 = new ExpenseResponse(52L, Category.CAR, 523, "Koła", localDate);
        List<ExpenseResponse> expenseResponses = List.of(expenseResponse, expenseResponse2);

        when(repository.findByLocalDate(localDate)).thenReturn(expenses);
        when(mapper.map(expenses)).thenReturn(expenseResponses);

        List<ExpenseResponse> actualList = service.findByLocalDate(localDate);

        Assertions.assertEquals(expenseResponses, actualList);
    }


}
