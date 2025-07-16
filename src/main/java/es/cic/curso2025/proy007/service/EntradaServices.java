package es.cic.curso2025.proy007.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso2025.proy007.model.Entrada;
import es.cic.curso2025.proy007.repository.EntradaRepository;

@Service
public class EntradaServices {
    
    @Autowired
    private EntradaRepository entradaRepository;

    public Long crear(Entrada entrada) {
        return entradaRepository.crear(entrada);
    }
}
