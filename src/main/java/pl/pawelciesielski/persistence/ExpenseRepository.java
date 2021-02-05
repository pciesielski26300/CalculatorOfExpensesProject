package pl.pawelciesielski.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;



public interface ExpenseRepository extends JpaRepository<Expense, Long> {


   List<Expense> findByCategoryOfExpense(Category categoryOfExpense);

   List<Expense> findByLocalDate(LocalDate localDate);
}
