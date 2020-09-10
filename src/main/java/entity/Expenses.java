package entity;


import javax.persistence.*;

@Entity
public class Expenses {
    @Id
    @GeneratedValue
    @OneToMany
    @JoinColumn(name = "EXPENSE_ID")
    private Long id;
    @Column(name = "categoryOfExpense")
    private String categoryOfExpense;
    @Column(name = "totalSumOfExpenses")
    private double totalSumOfExpenses;

}
