package es.cic.curso2025.proy007.service;

import java.util.List;

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

    public Entrada buscarUna(Long id){
        return entradaRepository.buscarUna(id);
    }

    public String eliminarUna(Long id) {
        return entradaRepository.eliminarUna(id);
    }

    public List<Entrada> listarTodas() {
        return entradaRepository.listarTodas();
    }

    public Entrada actualizarNombrePartido(Long id, String nuevoNombrePartido) {
        return entradaRepository.actualizarNombrePartido(id, nuevoNombrePartido);
    }
}
