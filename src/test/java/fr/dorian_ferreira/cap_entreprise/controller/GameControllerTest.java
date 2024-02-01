package fr.dorian_ferreira.cap_entreprise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccessGameGamer() throws Exception {
        mockMvc.perform(get("/jeu").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isOk())
                .andExpect(view().name("game/list"));
    }

    @Test
    public void testAccessGameAdmin() throws Exception {
        mockMvc.perform(get("/jeu").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("game/list"));
    }

    @Test
    public void testAccessGameShowGamer() throws Exception {
        mockMvc.perform(get("/jeu/minecraft").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isOk())
                .andExpect(view().name("game/show"));
    }

    @Test
    public void testAccessGameShowAdmin() throws Exception {
        mockMvc.perform(get("/jeu/minecraft").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("game/show"));
    }

}