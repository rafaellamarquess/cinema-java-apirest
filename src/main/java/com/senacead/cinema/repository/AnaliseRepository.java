package com.senacead.cinema.repository;

import com.senacead.cinema.model.AnaliseFilme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliseRepository extends JpaRepository<AnaliseFilme, Long> {

}