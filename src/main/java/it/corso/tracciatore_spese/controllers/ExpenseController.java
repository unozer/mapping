package it.corso.tracciatore_spese.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.tracciatore_spese.models.Category;
import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/expenses")
@Tag(name = "Gestione spese", description = "API di gestione movimenti spese")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @GetMapping
    @Operation(summary = "Recupera tutte le spese")
    @ApiResponse(responseCode = "200", description = "Spese recuperate con successo")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera spesa tramite id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupero avvenuto con successo"),
            @ApiResponse(responseCode = "400", description = "Spesa non trovata con quell'id")
    })
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    @Operation(summary = "Aggiunge una spesa al database")
    @ApiResponse(responseCode = "200", description = "Spesa aggiunta con successo")
    public ResponseEntity<Expense> createNewExpense(@RequestParam String movement,
                                                    @RequestParam float cash,
                                                    @RequestParam Date date,
                                                    @RequestParam Long category_id) {
        Expense newExpense = new Expense();
        newExpense.setMovement(movement);
        newExpense.setCash(cash);
        newExpense.setDate(date);
        Category cat = expenseService.getCategoryById(category_id);
        if (cat != null) {
            newExpense.setCategory(cat);
            expenseService.createNewExpense(newExpense);
            return ResponseEntity.status(HttpStatus.CREATED).body(newExpense);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica una spesa tramite id")
    @ApiResponse(responseCode = "200", description = "Spesa modificata con successo")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,
                                                 @RequestParam String movement,
                                                 @RequestParam float cash,
                                                 @RequestParam Date date,
                                                 @RequestParam Long category_id) {

        Expense updatedExpense = new Expense();
        updatedExpense.setId(id);
        updatedExpense.setMovement(movement);
        updatedExpense.setCash(cash);
        updatedExpense.setDate(date);
        Category cat = expenseService.getCategoryById(category_id);
        if (cat != null) {
            updatedExpense.setCategory(cat);
            expenseService.updateExpense(updatedExpense);
            return ResponseEntity.status(HttpStatus.OK).body(updatedExpense);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina spesa dal database")
    @ApiResponse(responseCode = "200", description = "Spesa eliminata con successo")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}