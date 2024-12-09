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

//        public Filme(Long id, String titulo, String sinopse, String genero, int anoLancamento) {
//            this.id = id;
//            this.titulo = titulo;
//            this.sinopse = sinopse;
//            this.genero = genero;
//            this.anoLancamento = anoLancamento;
//        }
}
