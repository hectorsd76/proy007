package es.cic.curso2025.proy007.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import es.cic.curso2025.proy007.model.Entrada;

@Repository
public class EntradaRepository {

    
    private Map<Long, Entrada> entradas = new HashMap<>();
    
    public Long crear(Entrada entrada) {
        Long mayor = getSiguienteId();
        entrada.setIdEntrada(mayor);
        entradas.put(entrada.getIdEntrada(), entrada);
        return entrada.getIdEntrada(); 
    }

    public Entrada buscarUna(Long id) {
        return entradas.get(id);
    }

    public String eliminarUna(Long id) {
        entradas.remove(id);
        
        return String.format("Se ha eliminado correctamente la entrada con id %d", id);
    }

    private Long getSiguienteId() {
        Long mayor = entradas.keySet()
                         .stream()
                         .max((a, b) -> a > b ? 1 : (a.equals(b) ? 0 : -1))
                         .orElse(0L);
    return mayor + 1;       
    }
}
