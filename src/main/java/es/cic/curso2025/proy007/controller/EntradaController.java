package es.cic.curso2025.proy007.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Long crear(Entrada entrada) {
        //TODO faltaria validar como el que no se mete una entrada que ya existe
        
        return entradaServices.crear(entrada);
    }
}
