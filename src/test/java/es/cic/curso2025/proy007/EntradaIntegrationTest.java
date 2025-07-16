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

    @BeforeEach
    void setUp(){
        entradaRepository.clearAll();
    }
    
    @Test
    public void crearTest() {
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(new Date());
        entrada.setNumAsiento(50);
        entrada.setPartido("Partido ficticio 1");
        Long id = entradaController.crear(entrada);

        assertTrue(id > 0);
    }

    @Test
    public void buscarUnaTest() {
        Entrada entrada1 = new Entrada();
        entrada1.setFechaPartido(new Date());
        entrada1.setNumAsiento(50);
        entrada1.setPartido("Partido ficticio 1");

        entradaController.crear(entrada1);

        Entrada entrada2 = new Entrada();
        entrada2.setFechaPartido(new Date());
        entrada2.setNumAsiento(50);
        entrada2.setPartido("Partido ficticio 2");

        entradaController.crear(entrada2);

        Entrada entrada3 = new Entrada();
        entrada3.setFechaPartido(new Date());
        entrada3.setNumAsiento(50);
        entrada3.setPartido("Partido ficticio 3");

        entradaController.crear(entrada3);

        Entrada entradaEncontrada = entradaController.buscarUna(2L);

        assertTrue("Partido ficticio 2".equals(entradaEncontrada.getPartido()));
    }

    @Test
    public void eliminarUnaTest() {
        Entrada entrada1 = new Entrada();
        entrada1.setFechaPartido(new Date());
        entrada1.setNumAsiento(50);
        entrada1.setPartido("Partido ficticio 1");

        entradaController.crear(entrada1);

        Entrada entrada2 = new Entrada();
        entrada2.setFechaPartido(new Date());
        entrada2.setNumAsiento(50);
        entrada2.setPartido("Partido ficticio 2");

        entradaController.crear(entrada2);

        Entrada entrada3 = new Entrada();
        entrada3.setFechaPartido(new Date());
        entrada3.setNumAsiento(50);
        entrada3.setPartido("Partido ficticio 3");

        entradaController.crear(entrada3);

        String entradaEliminada = entradaController.eliminarUna(1L);

        assertTrue("Se ha eliminado correctamente la entrada con id 1".equals(entradaEliminada));
    }


      @Test
    public void listarTodasTest() {
        
        Entrada entrada1 = new Entrada();
        entrada1.setFechaPartido(new Date());
        entrada1.setNumAsiento(301);
        entrada1.setPartido("Racing-Numancia");
        entradaController.crear(entrada1); 

        Entrada entrada2 = new Entrada();
        entrada2.setFechaPartido(new Date());
        entrada2.setNumAsiento(302);
        entrada2.setPartido("Oporto-Benfica");
        entradaController.crear(entrada2); 

   
        List<Entrada> todasLasEntradas = entradaController.listarTodas();

        assertEquals("Racing-Numancia", todasLasEntradas.get(0).getPartido());
        assertEquals(1L, todasLasEntradas.get(0).getIdEntrada());

        assertEquals("Oporto-Benfica", todasLasEntradas.get(1).getPartido());
        assertEquals(2L, todasLasEntradas.get(1).getIdEntrada());
    }




    @Test
    public void actualizarNombrePartidoTest() {

        Entrada entrada = new Entrada();
        entrada.setFechaPartido(new Date()); 
        entrada.setNumAsiento(150);
        entrada.setPartido("Partido Inicial");
        Long idCreado = entradaController.crear(entrada); 


        String nuevoNombre = "Partido Actualizado y Genial";
        Entrada entradaActualizada = entradaController.actualizarNombrePartido(idCreado, nuevoNombre);

        assertEquals(idCreado, entradaActualizada.getIdEntrada()); 
        assertEquals(nuevoNombre, entradaActualizada.getPartido());


    }

}
