package com.senacead.cinema.model;


import lombok.Data;

@Data
public class Filme {
        private Long id;
        private String titulo;
        private String sinopse;
        private String genero;
        private int anoLancamento;

        public Filme() {}

        public Filme(Long id, String titulo, String sinopse, String genero, int anoLancamento) {
            this.id = id;
            this.titulo = titulo;
            this.sinopse = sinopse;
            this.genero = genero;
            this.anoLancamento = anoLancamento;
        }

//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public String getTitulo() {
//            return titulo;
//        }
//
//        public void setTitulo(String titulo) {
//            this.titulo = titulo;
//        }
//
//        public String getSinopse() {
//            return sinopse;
//        }
//
//        public void setSinopse(String sinopse) {
//            this.sinopse = sinopse;
//        }
//
//        public String getGenero() {
//            return genero;
//        }
//
//        public void setGenero(String genero) {
//            this.genero = genero;
//        }
//
//        public int getAnoLancamento() {
//            return anoLancamento;
//        }
//
//        public void setAnoLancamento(int anoLancamento) {
//            this.anoLancamento = anoLancamento;
//        }
}
