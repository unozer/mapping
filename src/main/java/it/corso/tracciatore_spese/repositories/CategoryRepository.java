package it.corso.tracciatore_spese.repositories;

import it.corso.tracciatore_spese.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
