package es.cic.curso2025.proy007.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Entrada crear(@RequestBody Entrada entrada) {
        //TODO faltaria validar como el que no se mete una entrada que ya existe
        
        return entradaServices.create(entrada);
    }

    @GetMapping("/{id}")
    public Entrada buscarUna(@PathVariable Long id) {
        return entradaServices.get(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUna(@PathVariable Long id) {
        entradaServices.delete(id);
    }

    @GetMapping("/entrada") 
    public List<Entrada> listarTodas() {
        return entradaServices.get();
    }
    
    @PutMapping("/{id}") 
    public Entrada actualizarEntrada(  @PathVariable Long id, @RequestBody Entrada entrada) { 
        
        entrada.setIdEntrada(id); 
        
        return entradaServices.update(entrada);
    }
        
}
