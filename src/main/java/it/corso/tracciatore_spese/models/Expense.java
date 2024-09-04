package it.corso.tracciatore_spese.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense implements Serializable {
    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movement;
    private float cash;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
