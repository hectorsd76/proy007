package es.cic.curso2025.proy007.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso2025.proy007.model.Entrada;
import es.cic.curso2025.proy007.service.EntradaServices;

@RestController
@RequestMapping("/entrada")
public class EntradaController {
    
    @Autowired
    private EntradaServices entradaServices;

    @PostMapping
    public long create(Entrada entrada){
        // TODO
        return entradaServices.create(entrada);
    }
}
