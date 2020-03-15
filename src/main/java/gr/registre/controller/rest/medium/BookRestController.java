package gr.registre.controller.rest.medium;

import gr.registre.model.medium.Book;
import gr.registre.model.review.BookReview;
import gr.registre.service.medium.BookService;
import gr.registre.service.review.BookReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController extends AbstractMediumRestController<Book, BookReview> {

    public BookRestController(BookService bookService, BookReviewService bookReviewService) {
        super(bookService, bookReviewService);
    }
}
