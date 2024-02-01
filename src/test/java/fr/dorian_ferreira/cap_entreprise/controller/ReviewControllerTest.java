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
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccessReviewGamer() throws Exception {
        mockMvc.perform(get("/avis").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/index"));
    }

    @Test
    public void testAccessReviewAdmin() throws Exception {
        mockMvc.perform(get("/avis").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/index"));
    }

    @Test
    public void testAccessReviewShowGamer() throws Exception {
        mockMvc.perform(get("/avis/1").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/show"));
    }

    @Test
    public void testAccessReviewShowAdmin() throws Exception {
        mockMvc.perform(get("/avis/1").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/show"));
    }

}