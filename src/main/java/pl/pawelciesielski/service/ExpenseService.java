package pl.pawelciesielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pawelciesielski.api.dto.ExpenseRequest;
import pl.pawelciesielski.persistance.ExpenseRepository;

@Service
public class ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository expenseRepository;
    //uzytkownika ktorego nazwa nazywa sie abc

    @Autowired
    public ExpenseService(ExpenseMapper mapper, ExpenseRepository expenseRepository) {
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
    }

    public void save(ExpenseRequest expenseRequest){
      expenseRepository.save(mapper.map(expenseRequest));
    }
}
