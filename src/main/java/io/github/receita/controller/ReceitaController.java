package io.github.receita.controller;

import io.github.receita.entities.Receita;
import io.github.receita.persistence.CategoriaRepository;
import io.github.receita.persistence.ReceitaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaRepository receitaRepository;
    private final CategoriaRepository categoriaRepository;

    public ReceitaController(ReceitaRepository receitaRepository,
                             CategoriaRepository categoriaRepository) {
        this.receitaRepository = receitaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("receitas", receitaRepository.findAll());
        return "receitas/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("receita", new Receita());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "receitas/form";
    }

    @PostMapping
    public String salvar(
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam Long tempoPreparo,
            @RequestParam String dificuldade,
            @RequestParam("categoria.id") String categoriaId
    ) {

        Receita receita = new Receita();

        receita.setNome(nome);
        receita.setDescricao(descricao);
        receita.setTempoPreparo(Duration.ofMinutes(tempoPreparo));
        receita.setDificuldade(dificuldade);

        categoriaRepository.findById(java.util.UUID.fromString(categoriaId))
                .ifPresent(receita::setCategoria);

        receitaRepository.save(receita);

        return "redirect:/receitas";
    }
}