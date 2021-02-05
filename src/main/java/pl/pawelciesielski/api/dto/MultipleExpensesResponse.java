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
public class ExpensesResponse {
    private List<ExpenseResponse> expenses;
    public double getTotal(){
        Stream<Double> doubleStream = expenses
                .stream()
                .map(ExpenseResponse::getValue);
        return doubleStream
                .reduce(Double::sum)
                .orElse(0D);

    }
}
