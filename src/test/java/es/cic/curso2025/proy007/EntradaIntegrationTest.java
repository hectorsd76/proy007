package es.cic.curso2025.proy007;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso2025.proy007.controller.EntradaController;
import es.cic.curso2025.proy007.model.Entrada;
import es.cic.curso2025.proy007.repository.EntradaRepository;


@SpringBootTest
public class EntradaIntegrationTest {
    
    @Autowired
    private EntradaController entradaController;

    @Autowired
    private EntradaRepository entradaRepository;


    
    @Test
    public void crearTest() {
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(new Date());
        entrada.setNumAsiento(50);
        entrada.setPartido("Partido ficticio 1");
        Long id = entradaController.create(entrada);

        assertTrue(id > 0);
    }

}
