package gr.registre.controller;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import gr.registre.service.medium.AlbumService;
import gr.registre.service.medium.BookService;
import gr.registre.service.medium.GameService;
import gr.registre.service.medium.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private BookService bookService;

    private AlbumService albumService;

    private MovieService movieService;

    private GameService gameService;

    public HomeController(BookService bookService, AlbumService albumService,
                          MovieService movieService, GameService gameService) {

        this.bookService = bookService;
        this.albumService = albumService;
        this.movieService = movieService;
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("yearlyBooksCompleted", getYearlyMediaCompleted(bookService.findAllCompleted()));
        model.addAttribute("yearlyAlbumsCompleted", getYearlyMediaCompleted(albumService.findAllCompleted()));
        model.addAttribute("yearlyMoviesCompleted", getYearlyMediaCompleted(movieService.findAllCompleted()));
        model.addAttribute("yearlyGamesCompleted", getYearlyMediaCompleted(gameService.findAllCompleted()));

        return "home";
    }

    /**
     * Returns the number of total media completed in the current year from the given list.
     *
     * @param media the media
     * @param <M> the medium type
     * @param <R> the review type
     * @return the number of total media completed in the current year from the given list
     */
    private <M extends Medium<R>, R extends Review> int getYearlyMediaCompleted(List<M> media) {
        int count = 0;
        LocalDate currentDate = LocalDate.now();

        for (M m : media) {
            LocalDate dateCompleted = m.getDateCompleted();
            if (dateCompleted.getYear() == currentDate.getYear()) {
                count++;
            }
        }
        return count;
    }
}
