package gr.registre.controller;

import gr.registre.controller.rest.medium.BookRestController;
import gr.registre.model.medium.Book;
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
@WebMvcTest(BookRestController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRestControllerIntegrationTest implements MediumRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRestController bookRestController;

    @Test
    public void testGetMediaWhenMediaExist() throws Exception {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");

        List<Book> books = Collections.singletonList(book);

        given(bookRestController.getMedia()).willReturn(books);

        mvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetMediumById() throws Exception {
        Book book = new Book();
        book.setTitle("The Brothers Karamazov");
        book.setAuthor("Fyodor Dostoyevsky");

        given(bookRestController.getMedium(book.getId())).willReturn(book);

        mvc.perform(get("/api/books/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(book.getTitle())));
    }
}
