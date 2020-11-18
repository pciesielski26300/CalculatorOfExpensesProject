package pl.pawelciesielski.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private Long id;
    private Category categoryOfExpense;
    private double value;
    private String description;
    private LocalDate localDate;


}
