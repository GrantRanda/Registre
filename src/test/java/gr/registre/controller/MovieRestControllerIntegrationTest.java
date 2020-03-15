package gr.registre.controller;

import gr.registre.controller.rest.medium.MovieRestController;
import gr.registre.model.medium.Movie;
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
@WebMvcTest(MovieRestController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MovieRestControllerIntegrationTest implements MediumRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieRestController movieRestController;

    @Test
    public void testGetMediaWhenMediaExist() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDirector("Director");

        List<Movie> movies = Collections.singletonList(movie);

        given(movieRestController.getMedia()).willReturn(movies);

        mvc.perform(get("/api/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetMediumById() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Ran");
        movie.setDirector("Akira Kurosawa");

        given(movieRestController.getMedium(movie.getId())).willReturn(movie);

        mvc.perform(get("/api/movies/" + movie.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(movie.getTitle())));
    }
}
