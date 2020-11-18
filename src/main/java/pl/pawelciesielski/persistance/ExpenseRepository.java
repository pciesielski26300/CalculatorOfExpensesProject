package pl.pawelciesielski.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {


   List<Expense> findByCategoryOfExpense(Category categoryOfExpense);


   List<Expense> findByLocalDate(LocalDate localDate);
}
