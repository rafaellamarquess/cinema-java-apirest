package com.senacead.cinema.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "analisefilme")
public class AnaliseFilme {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "filme_id", nullable = false)
        private Filme filme;

        @Column(columnDefinition = "TEXT", nullable = false)
        private String analise;
        private Double nota;
}