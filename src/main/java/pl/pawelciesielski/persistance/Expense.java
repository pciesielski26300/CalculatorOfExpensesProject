package pl.pawelciesielski.persistance;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "EXPENSES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue
    @Column(name = "EXPENSE_ID")
    private Long id;
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category categoryOfExpense;
    @Column(name = "VALUE")
    private double value;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE")
    private LocalDate creationDate;


}
