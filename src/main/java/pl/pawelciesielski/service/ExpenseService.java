package pl.pawelciesielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.api.dto.ExpenseResponse;
import pl.pawelciesielski.persistance.Category;
import pl.pawelciesielski.persistance.Expense;
import pl.pawelciesielski.persistance.ExpenseRepository;

import java.time.OffsetDateTime;
import java.util.Objects;


@Service
public class ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository expenseRepository;


    @Autowired
    public ExpenseService(ExpenseMapper mapper, ExpenseRepository expenseRepository) {
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
    }

    public void save(Expense expense) {
        if (Objects.isNull(expense.getCategoryOfExpense()) ||
                Objects.isNull(expense.getDescription()) ||
                Objects.isNull(expense.getOffsetDateTime()) ||
                expense.getValue() == 0 ||
                Objects.isNull(expense.getId())) {
            throw new IllegalArgumentException("Null value!");
        } else {
            expenseRepository.save(expense);
        }
    }

    public void save(ExpenseRequest expenseRequest) {
        Expense expense = mapper.map(expenseRequest);
        expense.setOffsetDateTime(OffsetDateTime.now());
        save(expense);
    }


    public Expense findById(Long uuid) {
        return expenseRepository.findById(uuid).orElseThrow();
    }

    public ExpenseResponse findExpense(Long uuid) {
        final Expense expense = findById(uuid);
        return mapper.map(expense);
    }

    public Page<Expense> findByCategory(Category category, int page, int size) {
        return expenseRepository.findByCategory(category, PageRequest.of(page, size));
    }

}
