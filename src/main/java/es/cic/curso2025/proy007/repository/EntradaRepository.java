package es.cic.curso2025.proy007.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso2025.proy007.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,Long> {

    
    
}
