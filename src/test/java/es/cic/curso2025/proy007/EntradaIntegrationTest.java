package es.cic.curso2025.proy007;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso2025.proy007.controller.EntradaController;
import es.cic.curso2025.proy007.model.Entrada;

@SpringBootTest
public class EntradaIntegrationTest {
    
    @Autowired
    private EntradaController entradaController;
    
    @Test
    public void crearTest() {
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(new Date());
        entrada.setNumAsiento(50);
        entrada.setPartido("Partido ficticio 1");
        Long id = entradaController.crear(entrada);

        assertTrue(id > 0);
    }
}
