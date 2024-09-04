package it.corso.tracciatore_spese.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryType;

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    @Nullable
    private List<Expense> expenses;
    */
}
