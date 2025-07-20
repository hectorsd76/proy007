package es.cic.curso2025.proy007;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso2025.proy007.controller.EntradaController;
import es.cic.curso2025.proy007.model.Entrada;
import es.cic.curso2025.proy007.repository.EntradaRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; // Para status(), jsonPath(), etc.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // Para post, put, get, delete, etc.


@SpringBootTest
@AutoConfigureMockMvc
public class EntradaIntegrationTest {
    
     @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntradaController entradaController;

    @Test
    void testCrear() throws Exception {
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(LocalDate.now());
        entrada.setNumAsiento(21);
        entrada.setPartido("Partido 1");

        String entradaJson = objectMapper.writeValueAsString(entrada);

        mockMvc.perform(post("/entrada")
            .contentType("application/json")
            .content(entradaJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertTrue(
                        objectMapper.readValue(
                            result.getResponse().getContentAsString(), Entrada.class).getIdEntrada() 
                            > 0,"El id no puede ser 0");
                });
        
    }

    @Test
    void borrar() throws Exception {
        //creamos el objeto
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(LocalDate.now());
        entrada.setNumAsiento(21);
        entrada.setPartido("Partido 2");
        //creamos el string con el json para el body
        String entradaJson = objectMapper.writeValueAsString(entrada);

        //creamos y recogemos con MockMvc un registro
        MvcResult result = mockMvc.perform(post("/entrada")
            .contentType("application/json")
            .content(entradaJson))
            .andExpect(status().isOk())
            .andReturn();
        //recogemos el id generado para ese registro
        Long idCreado = objectMapper.readValue(result.getResponse().getContentAsString(), Entrada.class).getIdEntrada();
        //comprobamos el borrado de ese registro
        mockMvc.perform(delete("/entrada/" + idCreado))
            .andExpect(status().isOk())
            .andReturn();
        

    }

    @Test
    void buscarUnaPorIdTest() throws Exception {
        //creamos el objeto
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(LocalDate.now());
        entrada.setNumAsiento(21);
        entrada.setPartido("Partido 2");
        //creamos el string con el json para el body
        String entradaJson = objectMapper.writeValueAsString(entrada);

        //creamos y recogemos con MockMvc un registro
        MvcResult result = mockMvc.perform(post("/entrada")
            .contentType("application/json")
            .content(entradaJson))
            .andExpect(status().isOk())
            .andReturn();
        //recogemos el id generado para ese registro
        Long idCreado = objectMapper.readValue(result.getResponse().getContentAsString(), Entrada.class).getIdEntrada();
        //comprobamos la busqueda utilizando ese id
        MvcResult result2 = mockMvc.perform(get("/entrada/" + idCreado))
        .andExpect(status().isOk())
        .andReturn();
        /* comprobamos que la entrada que nos devuelve tiene el mismo partido que la entrada ingresada,
        ya que no podemos utilizar el equals porque la entrada que creamos a mano no tiene id
        */
        Entrada entradaEncoEntrada = objectMapper.readValue(result2.getResponse().getContentAsString(), Entrada.class);
        assertEquals(entradaEncoEntrada.getPartido(), entrada.getPartido(), "No coinciden los partidos");
    }

    @Test
    void actualizarEntradaTest() throws Exception{
        //creamos el objeto
        Entrada entrada = new Entrada();
        entrada.setFechaPartido(LocalDate.now());
        entrada.setNumAsiento(21);
        entrada.setPartido("Partido 2");
        // Convertimos el objeto Entrada  a json 
        String entradaJson = objectMapper.writeValueAsString(entrada);
        // Creamos y lo recogemos
        MvcResult result = mockMvc.perform(post("/entrada")
            .contentType("application/json")
            .content(entradaJson))
            .andExpect(status().isOk())
            .andReturn();
        // Recogemos el id generado anteriormente
        Long idCreado = objectMapper.readValue(result.getResponse().getContentAsString(),Entrada.class ).getIdEntrada();
        // Variable para actualizar la fecha 10 dias más a la anterior
        LocalDate fechaActualizadaEsperada = LocalDate.now().plusDays(10); 
        // Preparamos los nuevos valores para actualizar
        Entrada entradaActualizada = new Entrada();
        entradaActualizada.setIdEntrada(idCreado); 
        entradaActualizada.setFechaPartido(fechaActualizadaEsperada); 
        entradaActualizada.setNumAsiento(99); 
        entradaActualizada.setPartido("Partido 3");
        // Lo pasamos a formato JSON
        String entradaJsonActualizada = objectMapper.writeValueAsString(entradaActualizada);
        // Enviamos la actualización a nuestro ENDPOINT con PUT
        MvcResult resultActualizado = mockMvc.perform(put("/entrada/" +idCreado)
            .contentType("application/json")
            .content(entradaJsonActualizada))
            .andExpect(status().isOk())
            // Comprobamos con andExpect que nuestros valores esten en la respuesta
            .andExpect(jsonPath("$.idEntrada").value(idCreado)) 
            .andExpect(jsonPath("$.numAsiento").value(99)) 
            .andExpect(jsonPath("$.partido").value("Partido 3")) 
            .andExpect(jsonPath("$.fechaPartido").value(fechaActualizadaEsperada.toString())) 
            .andReturn();

        // Deserializamos la respuesta
        Entrada resultVerificado = objectMapper.readValue(resultActualizado.getResponse().getContentAsString(), Entrada.class);
        // Validamos campo a campo
        assertEquals(idCreado, resultVerificado.getIdEntrada(), "El ID no coincide");
        assertEquals(99, resultVerificado.getNumAsiento(), "Numero de asientos no actualiado");
        assertEquals("Partido 3", resultVerificado.getPartido(), "Partido no actualizado");
        assertEquals(fechaActualizadaEsperada, resultVerificado.getFechaPartido(), "La fecha no esta actualizada");

    }

}
