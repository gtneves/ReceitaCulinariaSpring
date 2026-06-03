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
    private String nome;

    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;

    @Column(name = "tempo_preparo", nullable = false)
    private Duration tempoPreparo;

    @Column(name = "dificuldade", length = 50,nullable = false)
    private String dificuldade;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
