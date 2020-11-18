package pl.pawelciesielski;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.pawelciesielski.api.ExpenseController;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.api.dto.ExpensesResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExpenseService service;
    @MockBean
    private ExpensesResponse expensesResponse;


    @Test
    public void save_allParamsOk_savedCorretclly() throws Exception {
        mockMvc.perform(
                post("/api/expense")
                        .content("{\n" +
                                "  \"categoryOfExpense\": \"CAR\",\n" +
                                "  \"value\": \"2323\",\n" +
                                "  \"description\": \"siema\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful());


        ExpenseRequest expense = ExpenseRequest
                .builder()
                .categoryOfExpense(Category.CAR)
                .value(2323)
                .description("siema")
                .build();

        verify(service, times(1)).save(expense);
    }

    //get nie podaje kontentu,
//delete z verify
    @Test
    public void findById_allParamsOk_foundExpense() throws Exception {
        ExpenseResponse expenseResponse = ExpenseResponse
                .builder()
                .categoryOfExpense(Category.CAR)
                .id(10005L)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        when(service.findExpense(10005L)).thenReturn(expenseResponse);
        mockMvc
                .perform(get("http://localhost:8080/api/expense?id=10005").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andDo(print())
                .andExpect(jsonPath("$.id").value(10005L))
                .andExpect(jsonPath("$.categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$.value").value(500))
                .andExpect(jsonPath("$.description").value("oc"))
                .andExpect(jsonPath("$.localDate").value("2020-10-26"));
    }

    @Test
    public void findAllExpensesByCategoryAndTotalValue_allParamsOk_foundExpense() throws Exception {
        ExpenseResponse expenseResponse = ExpenseResponse
                .builder()
                .categoryOfExpense(Category.CAR)
                .id(10012L)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        ExpenseResponse expenseResponse2 = ExpenseResponse
                .builder()
                .categoryOfExpense(Category.CAR)
                .id(10012L)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        List<ExpenseResponse> list = List.of(expenseResponse, expenseResponse2);
       double value = 1000;

        when(service.findByCategoryOfExpense(Category.CAR)).thenReturn(list);
        when(expensesResponse.getTotal()).thenReturn(value);

        mockMvc.perform(get("http://localhost:8080/api/expensesResponse?categoryOfExpense=CAR").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].expenses.id").value(10012))
                .andExpect(jsonPath("$[0].expenses.categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[0].expenses.value").value(500.0))
                .andExpect(jsonPath("$[0].expenses.description").value("oc"))
                .andExpect(jsonPath("$[0].expenses.localDate").value("2020-10-26"))
                .andExpect(jsonPath("$[1].expenses.id").value(10012))
                .andExpect(jsonPath("$[1].expenses.categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[1].expenses.value").value(500.0))
                .andExpect(jsonPath("$[1].expenses.description").value("oc"))
                .andExpect(jsonPath("$[1].expenses.localDate").value("2020-10-26"))
                .andExpect(jsonPath("$.total").value(1000));

    }

    @Test
    public void findByCategoryOfExpense_allParamsOk_foundExpenses() throws Exception {
        ExpenseResponse expenseResponse = ExpenseResponse
                .builder()
                .id(10012L)
                .categoryOfExpense(Category.CAR)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        ExpenseResponse expenseResponse2 = ExpenseResponse
                .builder()
                .id(10012L)
                .categoryOfExpense(Category.CAR)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        List<ExpenseResponse> list = List.of(expenseResponse, expenseResponse2);
        when(service.findByCategoryOfExpense(Category.CAR)).thenReturn(list);

        mockMvc.perform(get("http://localhost:8080/api/category?categoryOfExpense=CAR").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andDo(print())
                .andExpect(jsonPath("$[0].id").value(10012))
                .andExpect(jsonPath("$[0].categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[0].value").value(500.0))
                .andExpect(jsonPath("$[0].description").value("oc"))
                .andExpect(jsonPath("$[0].localDate").value("2020-10-26"))
                .andExpect(jsonPath("$[1].id").value(10012))
                .andExpect(jsonPath("$[1].categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[1].value").value(500.0))
                .andExpect(jsonPath("$[1].description").value("oc"))
                .andExpect(jsonPath("$[1].localDate").value("2020-10-26"));
    }

    @Test
    public void findByLocalDate_allParamsOk_foundExpenses() throws Exception {
        ExpenseResponse expenseResponse = ExpenseResponse
                .builder()
                .id(10012L)
                .categoryOfExpense(Category.CAR)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        ExpenseResponse expenseResponse2 = ExpenseResponse
                .builder()
                .id(10012L)
                .categoryOfExpense(Category.CAR)
                .value(500)
                .description("oc")
                .localDate(LocalDate.of(2020, 10, 26))
                .build();
        List<ExpenseResponse> list = List.of(expenseResponse, expenseResponse2);
        when(service.findByLocalDate(LocalDate.of(2020, 10, 26))).thenReturn(list);

        mockMvc.perform(get("http://localhost:8080/api/date?localDate=2020-10-26").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andDo(print())
                .andExpect(jsonPath("$[0].id").value(10012))
                .andExpect(jsonPath("$[0].categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[0].value").value(500.0))
                .andExpect(jsonPath("$[0].description").value("oc"))
                .andExpect(jsonPath("$[0].localDate").value("2020-10-26"))
                .andExpect(jsonPath("$[1].id").value(10012))
                .andExpect(jsonPath("$[1].categoryOfExpense").value("CAR"))
                .andExpect(jsonPath("$[1].value").value(500.0))
                .andExpect(jsonPath("$[1].description").value("oc"))
                .andExpect(jsonPath("$[1].localDate").value("2020-10-26"));
    }
    @Test
    public void delete_allParamsOk_deletedCorrectly() throws Exception {
     mockMvc.perform(delete("/api/expense/10007").contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().is2xxSuccessful())
             .andDo(print());


     verify(service, times(1)).deleteById(10007L);
    }
}
