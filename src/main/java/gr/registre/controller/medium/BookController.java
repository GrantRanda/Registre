package gr.registre.controller.medium;

import gr.registre.model.medium.Book;
import gr.registre.model.review.BookReview;
import gr.registre.service.medium.BookService;
import gr.registre.service.review.BookReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/books")
public class BookController extends AbstractMediumController<Book, BookReview> {

    public BookController(BookService bookService, BookReviewService bookReviewService) {
        super(bookService, bookReviewService);
        viewTable = "tables/book-table";
        viewForm = "forms/book-form";
        viewReviews = "reviews/book-reviews";
        viewReviewForm = "forms/book-review-form";
        modelSingular = "book";
        modelPlural = "books";
    }

    @Override
    public String editMedium(int id, Model model, boolean isCompleted) {
        Book book = null;

        if (id > 0) {
            book = mediumService.findById(id);
        }
        if (book == null) {
            book = new Book();

            if (isCompleted) {
                book.setIsCompleted(true);
                book.setDateCompleted(LocalDate.now());
            }
        }
        model.addAttribute(modelSingular, book);

        return viewForm;
    }

    @Override
    public String editReview(int id, Model model) {
        Book book = mediumService.findById(id);

        if (book.getReview() == null) {
            book.setReview(new BookReview());
        }

        model.addAttribute("title", book.getTitle());
        model.addAttribute("review", book.getReview());
        model.addAttribute(modelPlural, mediumService.findAllCompleted());

        return viewReviewForm;
    }
}
