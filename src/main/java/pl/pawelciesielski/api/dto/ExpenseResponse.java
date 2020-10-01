package pl.pawelciesielski.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawelciesielski.persistance.Category;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private Category categoryOfExpense;
    private double totalSumOfExpenses;
    private String description;
    private OffsetDateTime dateTime;
}
