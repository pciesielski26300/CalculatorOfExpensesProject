package pl.pawelciesielski.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

   Page<Expense> findByCategory(Category category, Pageable pageable);
}
