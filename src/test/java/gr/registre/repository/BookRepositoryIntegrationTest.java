package gr.registre.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gr.registre.model.category.BookCategory;
import gr.registre.model.medium.Book;
import gr.registre.model.review.BookReview;
import gr.registre.repository.medium.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositoryIntegrationTest implements MediumRepositoryIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void init() {
        Book b1 = new Book();
        b1.setTitle("B1");
        b1.setAuthor("B1 Author");
        b1.setPageCount(101);
        b1.setCategory(BookCategory.OTHER);
        b1.setIsCompleted(false);
        b1.setDateStarted(LocalDate.now());
        b1.setDateCompleted(null);
        b1.setRating(5);
        b1.setReview(null);

        Book b2 = new Book();
        b2.setTitle("B2");
        b2.setAuthor("B2 Author");
        b2.setPageCount(102);
        b2.setCategory(BookCategory.POETRY);
        b2.setIsCompleted(true);
        b2.setDateStarted(LocalDate.now());
        b2.setDateCompleted(LocalDate.now());
        b2.setRating(8);

        BookReview r1 = new BookReview();
        r1.setText("Review text");
        r1.setDateModified(LocalDate.now());
        r1.setBook(b2);

        b2.setReview(r1);

        bookRepository.save(b1);
        bookRepository.save(b2);
    }

    @Test
    @Override
    public void testFindAllWhenMediaExist() {
        List<Book> books = bookRepository.findAll();
        assertNotNull(books);
    }

    @Test
    @Override
    public void testFindMediumById() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        bookRepository.save(book);

        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        assertNotNull(optionalBook.orElse(null));
    }

    @Test
    @Override
    public void testFindMediumByInvalidId() {
        Optional<Book> optionalBook = bookRepository.findById(-1);
        assertNull(optionalBook.orElse(null));
    }

    @Test
    @Override
    public void testSaveMedium() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        bookRepository.save(book);
        assertNotNull(bookRepository.findById(book.getId()));
    }

    @Test
    @Override
    public void testDeleteMediumById() {
        bookRepository.deleteById(1);
        Optional<Book> optionalBook = bookRepository.findById(1);
        assertNull(optionalBook.orElse(null));
    }
}
