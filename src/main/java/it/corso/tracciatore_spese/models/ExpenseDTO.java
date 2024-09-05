package it.corso.tracciatore_spese.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO implements Serializable {
    private String movement;
    private float cash;
    private Date date;
    private Long category_id;
}
