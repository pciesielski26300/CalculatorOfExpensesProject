package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarExpenses {
    @Id
    @GeneratedValue
    @Column(name = "EXPENSE_ID")
    private long id;
    @Column
    private String shortDescription;
}
