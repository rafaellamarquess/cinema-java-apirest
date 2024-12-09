package com.senacead.cinema.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Filme {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String sinopse;
        private String genero;
        private int anoLancamento;

        @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Analise> analises;

        public Filme() {}
}
