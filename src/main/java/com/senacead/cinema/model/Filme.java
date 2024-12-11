package com.senacead.cinema.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Filme {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String sinopse;
        private String genero;
        private int anoLancamento;

        @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<AnaliseFilme> analises;
}
