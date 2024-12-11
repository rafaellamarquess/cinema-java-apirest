package com.senacead.cinema.controller;

import com.senacead.cinema.model.AnaliseFilme;
import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.AnaliseRepository;
import com.senacead.cinema.repository.FilmeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analises")
public class AnaliseController {
    private final List<AnaliseFilme> analises = new ArrayList<>();
    private final Long nextId = 1L;

    private final AnaliseRepository analiseRepository;
    private final FilmeRepository filmeRepository;

    public AnaliseController(AnaliseRepository analiseRepository, FilmeRepository filmeRepository) {
        this.analiseRepository = analiseRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public String listarAnalises(Model model) {
        final List<AnaliseFilme> analises = analiseRepository.findAll();
        model.addAttribute("analises", analises);
        return "analises/lista";
    }

    // Exibe o formulário de adição de análise
    @GetMapping("/nova")
    public String exibirFormularioAnalise(Model model) {
        model.addAttribute("analise", new AnaliseFilme());
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "analises/formulario";
    }

    // Cadastra nova análise
    @PostMapping
    public String salvarAnalise(
            @RequestParam Long filmeId,
            @RequestParam String analise,
            @RequestParam Double nota,
            RedirectAttributes redirectAttributes) {
        // Busca o filme pelo ID
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new IllegalArgumentException("ID de filme inválido: " + filmeId));

        // Cria e preenche o objeto AnaliseFilme
        AnaliseFilme analiseFilme = new AnaliseFilme();
        analiseFilme.setFilme(filme);
        analiseFilme.setAnalise(analise);
        analiseFilme.setNota(nota);

        // Salva no repositório
        analiseRepository.save(analiseFilme);

        // Adiciona mensagem de sucesso
        redirectAttributes.addFlashAttribute("message", "Análise salva com sucesso!");
        return "redirect:/analises";
    }


    // Exibir o formulário de edição de análise
    @GetMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        AnaliseFilme analiseFilme = analiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análise não encontrada"));
        model.addAttribute("analise", analiseFilme);
        model.addAttribute("filmes", filmeRepository.findAll());
        return "analises/editar";
    }

    // Atualiza analise existente
    @PostMapping("/{id}")
    public String atualizarAnalise(
            @PathVariable Long id,
            @RequestParam Long filmeId,
            @RequestParam String analise,
            @RequestParam Double nota,
            Model model) {
        AnaliseFilme analiseFilme = analiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de análise inválido: " + id));
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new IllegalArgumentException("ID de filme inválido: " + filmeId));
        analiseFilme.setFilme(filme);
        analiseFilme.setAnalise(analise);
        analiseFilme.setNota(nota);
        analiseRepository.save(analiseFilme);
        return "redirect:/analises";
    }

    // Deletar análise
    @GetMapping("/deletar/{id}")
    public String deletarAnalise(@PathVariable Long id) {
        analiseRepository.deleteById(id); // Deleta a análise pelo ID
        return "redirect:/analises"; // Redireciona para a lista de análises
    }
}
