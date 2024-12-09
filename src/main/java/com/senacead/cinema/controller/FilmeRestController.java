package com.senacead.cinema.controller;

import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {
    private final FilmeRepository filmeRepository;

    public FilmeRestController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    @PostMapping
    public Filme adicionarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setSinopse(filmeAtualizado.getSinopse());
            filme.setGenero(filmeAtualizado.getGenero());
            filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            return ResponseEntity.ok(filmeRepository.save(filme));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFilme(@PathVariable Long id) {
        return filmeRepository.findById(id).map(filme -> {
            filmeRepository.delete(filme);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
