package com.senacead.cinema.model;

import lombok.Data;

@Data
public class Analise {

        private Long id;
        private Filme filme;
        private String analise;
        private double nota;


        public Analise() {}

        public Analise(Long id, Filme filme, String analise, double nota) {
            this.id = id;
            this.filme = filme;
            this.analise = analise;
            this.nota = nota;
        }

//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public Filme getFilme() {
//            return filme;
//        }
//
//        public void setFilme(Filme filme) {
//            this.filme = filme;
//        }
//
//        public String getAnalise() {
//            return analise;
//        }
//
//        public void setAnalise(String analise) {
//            this.analise = analise;
//        }
//
//        public double getNota() {
//            return nota;
//        }
//
//        public void setNota(double nota) {
//            this.nota = nota;
//        }
}
