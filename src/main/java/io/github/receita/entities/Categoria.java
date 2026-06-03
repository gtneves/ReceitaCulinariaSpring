package io.github.receita.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Categoria {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Receita> receitas;
}
