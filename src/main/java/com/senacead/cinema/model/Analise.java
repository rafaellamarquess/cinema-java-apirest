package com.senacead.cinema.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Analise {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "filme_id", nullable = false)
        private Filme filme;

        private String analise;
        private Double nota;

        public Analise() {}

        public Analise(Long id, Filme filme, String analise, double nota) {
            this.id = id;
            this.filme = filme;
            this.analise = analise;
            this.nota = nota;
        }
}
