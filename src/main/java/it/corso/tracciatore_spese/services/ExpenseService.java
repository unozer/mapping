package it.corso.tracciatore_spese.services;

import it.corso.tracciatore_spese.models.Category;
import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.repositories.CategoryRepository;
import it.corso.tracciatore_spese.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense createNewExpense(String movement, float cash, Date date, Category category) {
        Expense newExpense = new Expense(null, movement, cash, date, category);
        return expenseRepository.save(newExpense);
    }

    public Expense createNewExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, String movement, float cash, Date date, Category category) {
        Expense updatedExpense = new Expense(id, movement, cash, date, category);
        return expenseRepository.save(updatedExpense);
    }

    public Expense updateExpense(Expense updatedExpense) {
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
