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
}
