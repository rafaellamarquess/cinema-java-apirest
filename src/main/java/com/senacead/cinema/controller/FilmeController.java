package com.senacead.cinema.controller;

import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final List<Filme> filmes = new ArrayList<>();
    private Long nextId = 1L;

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public String listarFilmes(Model model) {
        final List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "filmes/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/formulario";
    }

    @PostMapping("/salvar")
    public String salvarFilme(Filme filme, Model model) {
        filmeRepository.save(filme);
        model.addAttribute("mensagem", "Filme salvo com sucesso!");
        return "redirect:/filmes";
    }

    @GetMapping("/editar")
    public String editarFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/editar";
    }
}