package com.senacead.cinema.controller;

import com.senacead.cinema.model.Analise;
import com.senacead.cinema.model.Filme;
import com.senacead.cinema.repository.AnaliseRepository;
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

    public AnaliseController(AnaliseRepository analiseRepository) {
        this.analiseRepository = analiseRepository;
    }

    @GetMapping
    public String listarAnalises(Model model) {
        final List<Analise> analises = analiseRepository.findAll();
        model.addAttribute("analises", analises);
        return "analises/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioAnalise(Model model) {
        model.addAttribute("analise", new Analise());
        return "analises/formulario";
    }

    @GetMapping("editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        Analise analise = analises.stream()
                .filter(a -> a.getId().equals(id)) // Encontre a análise pelo ID
                .findFirst()
                .orElse(null); // Retorna null se não encontrar a análise
        if (analise != null) {
            model.addAttribute("analise", analise);
            return "analises/editar"; // Retorna a página de edição
        } else {
            return "redirect:/analises"; // Redireciona de volta para a lista caso não encontre
        }
    }

    @PostMapping
    public String cadastrarAnalise(@ModelAttribute Analise analise) {
        analise.setId(nextId++);
        analises.add(analise);
        return "redirect:/analises";
    }
}
