package com.senacead.cinema.controller;

import com.senacead.cinema.model.Analise;
import com.senacead.cinema.repository.AnaliseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {
    private final AnaliseRepository analiseRepository;

    public AnaliseRestController(AnaliseRepository analiseRepository) {
        this.analiseRepository = analiseRepository;
    }

    @GetMapping
    public List<Analise> listarTodas() {
        return analiseRepository.findAll();
    }

    @PostMapping
    public Analise adicionarAnalise(@RequestBody Analise analise) {
        return analiseRepository.save(analise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analise> atualizarAnalise(@PathVariable Long id, @RequestBody Analise analiseAtualizada) {
        return analiseRepository.findById(id).map(analise -> {
            analise.setAnalise(analiseAtualizada.getAnalise());
            analise.setNota(analiseAtualizada.getNota());
            return ResponseEntity.ok(analiseRepository.save(analise));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAnalise(@PathVariable Long id) {
        return analiseRepository.findById(id).map(analise -> {
            analiseRepository.delete(analise);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
