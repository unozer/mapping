package it.corso.tracciatore_spese.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO implements Serializable {
    private String movement;
    private float cash;
    private Long category_id;
}

