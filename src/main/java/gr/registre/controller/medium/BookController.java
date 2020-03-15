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

    //    @GetMapping("/read")
//    public String booksRead(Model model, @RequestParam("page") Optional<Integer> page,
//                            @RequestParam("size") Optional<Integer> size) {
//
//        int pageNumber = page.orElse(1);
//        int pageSize = size.orElse(50);
//        List<Book> books = bookService.findAllCompleted();
//
//        addPageAttributes(pageNumber, pageSize, books, model);
//        model.addAttribute("isCompleted", true);
//
//        return "tables/book-table";
//    }
//
//    @GetMapping("/to-read")
//    public String booksToRead(Model model, @RequestParam("page") Optional<Integer> page,
//                              @RequestParam("size") Optional<Integer> size) {
//
//        int pageNumber = page.orElse(1);
//        int pageSize = size.orElse(50);
//        List<Book> books = bookService.findAllUncompleted();
//
//        addPageAttributes(pageNumber, pageSize, books, model);
//        model.addAttribute("isCompleted", false);
//
//        return "tables/book-table";
//    }
//
//    @GetMapping("/reviews")
//    public String bookReviews(Model model, @RequestParam("page") Optional<Integer> page,
//                              @RequestParam("size") Optional<Integer> size) {
//
//        int pageNumber = page.orElse(1);
//        int pageSize = size.orElse(50);
//        List<Book> books = bookService.findAllCompleted();
//
//        addPageAttributes(pageNumber, pageSize, books, model);
//        model.addAttribute("books", bookService.findAllCompleted());
//
//        return "reviews";
//    }
//
//    @GetMapping("/read/add")
//    public String addBookRead(Model model) {
//        Book book = new Book();
//        book.setIsCompleted(true);
//        book.setDateCompleted(LocalDate.now());
//
//        model.addAttribute("book", book);
//
//        return "forms/book-form";
//    }
//
//    @GetMapping("/to-read/add")
//    public String addBookToRead(Model model) {
//        Book book = new Book();
//        book.setIsCompleted(false);
//
//        model.addAttribute("book", book);
//
//        return "forms/book-form";
//    }
//
//    /**
//     * Marks a book as read. Returns the book form path and sets the date completed field
//     * to the current date.
//     *
//     * @param id    the ID of the book to mark read
//     * @param model the model
//     * @return the path of the book form
//     */
//    @GetMapping("/to-read/mark-read")
//    public String markBookRead(@RequestParam("bookId") int id, Model model) {
//        Book book = bookService.findById(id);
//        book.setIsCompleted(true);
//        book.setDateCompleted(LocalDate.now());
//
//        model.addAttribute("book", book);
//
//        return "forms/book-form";
//    }
//
//    @GetMapping("/delete")
//    public String deleteBook(@RequestParam("bookId") int id) {
//        boolean isRead = bookService.findById(id).getIsCompleted();
//        bookService.deleteById(id);
//
//        return isRead ? "redirect:/books/read" : "redirect:/books/to-read";
//    }
//
//    @GetMapping("/reviews/delete")
//    public String deleteBookReview(@RequestParam("bookId") int id) {
//        Book book = bookService.findById(id);
//        int reviewId = book.getReview().getId();
//
//        book.setReview(null);
//        bookService.save(book);
//        reviewService.deleteById(reviewId);
//
//        return "redirect:/books/reviews";
//    }
//
//    @GetMapping("/edit")
//    public String editBook(@RequestParam("bookId") int id, Model model) {
//        model.addAttribute("book", bookService.findById(id));
//
//        return "forms/book-form";
//    }
//
//    @GetMapping("/review")
//    public String reviewBook(@RequestParam("bookId") int id, Model model) {
//        Book book = bookService.findById(id);
//
//        if (book.getReview() == null) {
//            book.setReview(new Review());
//        }
//
//        model.addAttribute("title", book.getTitle());
//        model.addAttribute("review", book.getReview());
//        model.addAttribute("books", bookService.findAllCompleted());
//
//        return "forms/review-form";
//    }
//
//    @PostMapping("/save")
//    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "forms/book-form";
//        }
//
//        bookService.save(book);
//
//        return book.getIsCompleted() ? "redirect:/books/read" : "redirect:/books/to-read";
//    }
//
//    @PostMapping("/reviews/save")
//    public String saveBookReview(@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, @RequestParam("bookId") int id, Model model) {
//        Book book = bookService.findById(id);
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("title", book.getTitle());
//            return "forms/review-form";
//        }
//
//        reviewService.save(review);
//        book.setReview(review);
//        bookService.save(book);
//
//        return "redirect:/books/reviews";
//    }
//
//    /**
//     * Adds a Page and page numbers as attributes to the given Model to be used in pagination.
//     *
//     * @param pageNumber the current page number
//     * @param pageSize   the current page size. That is, the number of table items displayed
//     * @param books      the books to be displayed
//     * @param model      the model
//     */
//    public void addPageAttributes(int pageNumber, int pageSize, List<Book> books, Model model) {
//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(pageNumber - 1, pageSize), books);
//
//        model.addAttribute("page", bookPage);
//
//        // Calculate the range of page numbers to display in pagination
//        int totalPages = bookPage.getTotalPages();
//        int number = bookPage.getNumber();
//        int currentPage = number + 3;
//        int startPage = number > totalPages - 5 ? Math.max(1, totalPages - 8) : Math.max(1, currentPage - 6);
//        int endPage = Math.min(startPage + 8, totalPages);
//
//        if (endPage > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//    }
}
