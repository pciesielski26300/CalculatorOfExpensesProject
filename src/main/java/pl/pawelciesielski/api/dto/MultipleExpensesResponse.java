package pl.pawelciesielski.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultipleExpensesResponse {
    private List<ExpenseResponse> expenses;

    public double getTotal() {
        return expenses
                .stream()
                .map(ExpenseResponse::getValue)
                .reduce(Double::sum)
                .orElse(0D);

    }
}
