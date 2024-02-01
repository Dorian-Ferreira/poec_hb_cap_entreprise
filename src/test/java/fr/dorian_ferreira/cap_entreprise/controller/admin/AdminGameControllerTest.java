package fr.dorian_ferreira.cap_entreprise.controller.admin;

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
public class AdminGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccessAdminGameEditGamer() throws Exception {
        mockMvc.perform(get("/admin/jeu/changer/1").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAccessAdminGameEditAdmin() throws Exception {
        mockMvc.perform(get("/admin/jeu/changer/1").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/game/form"));
    }

    @Test
    public void testAccessAdminGameNewGamer() throws Exception {
        mockMvc.perform(get("/admin/jeu/nouveau").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAccessAdminGameNewAdmin() throws Exception {
        mockMvc.perform(get("/admin/jeu/nouveau").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/game/form"));
    }

    @Test
    public void testAccessAdminGameImageGamer() throws Exception {
        mockMvc.perform(get("/admin/jeu/image/1").with(user("bobsledd").roles("GAMER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAccessAdminGameImageAdmin() throws Exception {
        mockMvc.perform(get("/admin/jeu/image/1").with(user("skipdover").roles("MODERATOR")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/game/image_upload"));
    }

}