package it.corso.tracciatore_spese.repositories;

import it.corso.tracciatore_spese.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
