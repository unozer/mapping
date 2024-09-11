package it.corso.tracciatore_spese.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.tracciatore_spese.models.Category;
import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
@Tag(name = "Gestione categorie", description = "API di gestione categorie di spesa")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @GetMapping
    @Operation(summary = "Recupera tutte le categorie")
    @ApiResponse(responseCode = "200", description = "Categorie recuperate con successo")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera categoria tramite id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupero avvenuto con successo"),
            @ApiResponse(responseCode = "400", description = "Categoria non trovata con quell'id")
    })
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @Operation(summary = "Aggiunge una categoria al database")
    @ApiResponse(responseCode = "200", description = "Categoria aggiunta con successo")
    public ResponseEntity<Category> createNewCategory(@RequestBody String categoryType) {
        Category newCategory = categoryService.createNewCategory(categoryType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica una categoria tramite id")
    @ApiResponse(responseCode = "200", description = "Categoria modificata con successo")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestBody String categoryType) {
        Category updatedCategory = categoryService.updateCategory(id, categoryType);
        return updatedCategory;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina categoria dal database")
    @ApiResponse(responseCode = "200", description = "Categoria eliminata con successo")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}