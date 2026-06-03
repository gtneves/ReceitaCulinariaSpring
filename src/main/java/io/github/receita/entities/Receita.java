package io.github.receita.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Receita {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    String nome;

    @Column(name = "descricao", length = 100, nullable = false)
    String descricao;

    @Column(name = "tempoPreparo", nullable = false)
    Duration tempoPreparo;

    @Column(name = "dificuldade", length = 50,nullable = false)
    String dificuldade;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
