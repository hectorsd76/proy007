package es.cic.curso2025.proy007.repository;

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





    private long getSiguienteId() {
        long mayor = 
            entradas
                .keySet()
                .stream()
                .max(
                    (primero, segundo) -> (int) (segundo.longValue() - primero.longValue())
                ).get();
        return mayor + 1;        
    }
}
