package es.cic.curso2025.proy007.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso2025.proy007.model.Entrada;
import es.cic.curso2025.proy007.repository.EntradaRepository;

@Service
public class EntradaServices {
    
    @Autowired
    private EntradaRepository entradaRepository;

    public Entrada create(Entrada entrada) {
         
       return entradaRepository.save(entrada);
        
    }

    public Entrada get(Long id){

        Optional<Entrada> resultado = entradaRepository.findById(id);
        return resultado.orElse(null);
    }

    public void delete(Long id) {
         entradaRepository.deleteById(id);
    }

    public List<Entrada> get() {
        return entradaRepository.findAll();
    }

    public Entrada  update(Entrada entrada){
        return entradaRepository.save(entrada);
    }
}
