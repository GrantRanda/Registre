package gr.registre.controller;

import gr.registre.controller.rest.medium.GameRestController;
import gr.registre.model.medium.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameRestController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GameRestControllerIntegrationTest implements MediumRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameRestController gameRestController;

    @Test
    public void testGetMediaWhenMediaExist() throws Exception {
        Game game = new Game();
        game.setTitle("Title");
        game.setDeveloper("Developer");

        List<Game> games = Collections.singletonList(game);

        given(gameRestController.getMedia()).willReturn(games);

        mvc.perform(get("/api/games")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetMediumById() throws Exception {
        Game game = new Game();
        game.setTitle("Myst");
        game.setDeveloper("Cyan Worlds");

        given(gameRestController.getMedium(game.getId())).willReturn(game);

        mvc.perform(get("/api/games/" + game.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(game.getTitle())));
    }
}
