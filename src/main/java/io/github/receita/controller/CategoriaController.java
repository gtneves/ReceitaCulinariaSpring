package io.github.receita.controller;

import io.github.receita.entities.Categoria;
import io.github.receita.persistence.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Categoria categoria) {
        repository.save(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable UUID id) {
        repository.deleteById(id);
        return "redirect:/categorias";
    }
}
