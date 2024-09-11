package it.corso.tracciatore_spese.services;

import it.corso.tracciatore_spese.mappers.ExpenseMapper;
import it.corso.tracciatore_spese.models.Category;
import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.models.ExpenseDTO;
import it.corso.tracciatore_spese.repositories.CategoryRepository;
import it.corso.tracciatore_spese.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private final ExpenseMapper expenseMapper = ExpenseMapper.INSTANCE;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense createNewExpense(ExpenseDTO expenseDTO) {

        categoryRepository.findById(expenseDTO.getCategory_id()).orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        Expense expense = expenseMapper.dtoToEntity(expenseDTO);
        expense = expenseRepository.save(expense);

        return expense;
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Spesa non trovata"));
        Expense updatedExpense = expenseMapper.dtoToEntity(expenseDTO);
        updatedExpense.setId(id);
        return expenseRepository.save(updatedExpense);
    }

    public boolean deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null) {
            expenseRepository.delete(expense);
            return true;
        }
        return false;
    }

    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }
}
