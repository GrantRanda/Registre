package gr.registre.controller;

import gr.registre.controller.rest.medium.AlbumRestController;
import gr.registre.model.medium.Album;
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
@WebMvcTest(AlbumRestController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AlbumRestControllerIntegrationTest implements MediumRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AlbumRestController albumRestController;

    @Test
    public void testGetMediaWhenMediaExist() throws Exception {
        Album album = new Album();
        album.setTitle("Title");
        album.setArtist("Artist");

        List<Album> albums = Collections.singletonList(album);

        given(albumRestController.getMedia()).willReturn(albums);

        mvc.perform(get("/api/albums")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetMediumById() throws Exception {
        Album album = new Album();
        album.setTitle("Magic Machine");
        album.setArtist("An Endless Sporadic");

        given(albumRestController.getMedium(album.getId())).willReturn(album);

        mvc.perform(get("/api/albums/" + album.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(album.getTitle())));
    }
}
