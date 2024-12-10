package com.senacead.cinema.controller;

import com.senacead.cinema.model.Analise;
import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.AnaliseRepository;
import com.senacead.cinema.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analises")
public class AnaliseController {
    private final List<Analise> analises = new ArrayList<>();
    private Long nextId = 1L;

    private final AnaliseRepository analiseRepository;
    private final FilmeRepository filmeRepository;

    public AnaliseController(AnaliseRepository analiseRepository, FilmeRepository filmeRepository) {
        this.analiseRepository = analiseRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public String listarAnalises(Model model) {
        final List<Analise> analises = analiseRepository.findAll();
        model.addAttribute("analises", analises);
        return "analises/lista";
    }

    // Exibir o formulário de adição de análise
    @GetMapping("/nova")
    public String exibirFormularioAnalise(Model model) {
        model.addAttribute("analise", new Analise());
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "analises/formulario";
    }

    // Cdastra uma nova análise
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> salvarAnalise(@RequestBody Analise analise) {
        analiseRepository.save(analise);
        return ResponseEntity.ok("Análise salva com sucesso!");
    }

    // Exibir o formulário de edição de análise
    @GetMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        Analise analise = analiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análise não encontrada!"));
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("analise", analise);
        model.addAttribute("filmes", filmes);
        return "analises/editar";
    }


    // Atualizar análise existente
    @PutMapping("/editar/{id}")
    @ResponseBody
    public String atualizarAnalise(@PathVariable Long id, @RequestBody Analise analiseAtualizada) {
        return analiseRepository.findById(id).map(analise -> {
            analise.setFilme(analiseAtualizada.getFilme());
            analise.setAnalise(analiseAtualizada.getAnalise());
            analise.setNota(analiseAtualizada.getNota());
            analiseRepository.save(analise);
            return "Análise atualizada com sucesso!";
        }).orElseThrow(() -> new RuntimeException("Análise não encontrada!"));
    }

    // Deletar análise
    @GetMapping("/deletar/{id}")
    public String deletarAnalise(@PathVariable Long id) {
        analiseRepository.deleteById(id); // Deleta a análise pelo ID
        return "redirect:/analises"; // Redireciona para a lista de análises
    }
}
