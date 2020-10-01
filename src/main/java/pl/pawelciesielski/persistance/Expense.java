package pl.pawelciesielski.persistance;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "EXPENSES")
@Builder
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue
    @Column(name = "EXPENSE_ID")
    private UUID id;
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category categoryOfExpense;
    @Column(name = "VALUE")
    private double totalSumOfExpenses;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column
    private OffsetDateTime dateTime;




}
