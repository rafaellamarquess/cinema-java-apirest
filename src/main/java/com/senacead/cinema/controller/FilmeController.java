package com.senacead.cinema.controller;

import com.senacead.cinema.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
    private List<Filme> filmes = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "filmes/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/formulario";
    }

    @PostMapping
    public String cadastrarFilme(@ModelAttribute Filme filme) {
        filme.setId(nextId++);
        filmes.add(filme);
        return "redirect:/filmes";
    }
}