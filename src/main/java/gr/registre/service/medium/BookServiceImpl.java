package gr.registre.service.medium;

import gr.registre.model.medium.Book;
import gr.registre.model.review.BookReview;
import gr.registre.repository.medium.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends AbstractMediumServiceImpl<Book, BookReview> implements BookService {

    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
    }
}
