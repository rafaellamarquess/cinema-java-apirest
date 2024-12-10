package com.senacead.cinema.controller;

import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Exibir o formulário de adição de novo filme
    @GetMapping("/novo")
    public String exibirFormularioFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/formulario";
    }

    // Cadastrar novo filme
    @PostMapping
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    // Exibir o formulário de edição de filme
    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        model.addAttribute("filme", filme);
        return "filmes/editar";
    }

    // Atualizar filme existente
    @PostMapping("/{id}")
    public String atualizarFilme(@PathVariable Long id, @ModelAttribute Filme filmeAtualizado, RedirectAttributes redirectAttributes) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setSinopse(filmeAtualizado.getSinopse());
            filme.setGenero(filmeAtualizado.getGenero());
            filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filmeRepository.save(filme);

            redirectAttributes.addFlashAttribute("message", "Filme atualizado com sucesso!");
            return "redirect:/filmes"; // Redireciona para a lista de filmes
        }).orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    // Deletar filme
    @GetMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id); // Deleta o filme pelo ID
        return "redirect:/filmes"; // Redireciona para a lista de filmes

    }
}

