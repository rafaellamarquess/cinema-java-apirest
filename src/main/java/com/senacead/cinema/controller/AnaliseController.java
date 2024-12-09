package com.senacead.cinema.controller;

import com.senacead.cinema.model.Analise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analises")
public class AnaliseController {
    private List<Analise> analises = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String listarAnalises(Model model) {
        model.addAttribute("analises", analises);
        return "analises/lista";
    }

    @GetMapping("/nova")
    public String exibirFormularioAnalise(Model model) {
        model.addAttribute("analise", new Analise());
        return "analises/formulario";
    }

    @PostMapping
    public String cadastrarAnalise(@ModelAttribute Analise analise) {
        analise.setId(nextId++);
        analises.add(analise);
        return "redirect:/analises";
    }
}
