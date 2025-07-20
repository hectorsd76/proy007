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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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

}
