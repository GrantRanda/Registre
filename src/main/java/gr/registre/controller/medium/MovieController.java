package gr.registre.controller.medium;

import gr.registre.model.medium.Movie;
import gr.registre.model.review.MovieReview;
import gr.registre.service.medium.MovieService;
import gr.registre.service.review.GameReviewService;
import gr.registre.service.review.MovieReviewService;
import gr.registre.service.review.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/movies")
public class MovieController extends AbstractMediumController<Movie, MovieReview> {

    public MovieController(MovieService movieService, MovieReviewService movieReviewService) {
        super(movieService, movieReviewService);
        viewTable = "tables/movie-table";
        viewForm = "forms/movie-form";
        viewReviews = "reviews/movie-reviews";
        viewReviewForm = "forms/movie-review-form";
        modelSingular = "movie";
        modelPlural = "movies";
    }

    @Override
    public String editMedium(int id, Model model, boolean isCompleted) {
        Movie movie = null;

        if (id > 0) {
            movie = mediumService.findById(id);
        }
        if (movie == null) {
            movie = new Movie();

            if (isCompleted) {
                movie.setIsCompleted(true);
                movie.setDateCompleted(LocalDate.now());
            }
        }
        model.addAttribute(modelSingular, movie);

        return viewForm;
    }

    @Override
    public String editReview(int id, Model model) {
        Movie movie = mediumService.findById(id);

        if (movie.getReview() == null) {
            movie.setReview(new MovieReview());
        }

        model.addAttribute("title", movie.getTitle());
        model.addAttribute("review", movie.getReview());
        model.addAttribute(modelPlural, mediumService.findAllCompleted());

        return viewReviewForm;
    }
}
