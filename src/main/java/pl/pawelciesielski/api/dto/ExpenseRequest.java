package pl.pawelciesielski.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawelciesielski.persistance.Category;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class
ExpenseRequest {
    private Long id;
    private Category categoryOfExpense;
    private double value;
    private String description;

}
