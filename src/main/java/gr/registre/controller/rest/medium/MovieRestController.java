package gr.registre.controller.rest.medium;

import gr.registre.model.medium.Movie;
import gr.registre.model.review.MovieReview;
import gr.registre.service.medium.MovieService;
import gr.registre.service.review.MovieReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController extends AbstractMediumRestController<Movie, MovieReview> {

    public MovieRestController(MovieService movieService, MovieReviewService movieReviewService) {
        super(movieService, movieReviewService);
    }
}
