package gr.registre.service.medium;

import gr.registre.model.medium.Movie;
import gr.registre.model.review.MovieReview;
import gr.registre.repository.medium.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends AbstractMediumServiceImpl<Movie, MovieReview> implements MovieService {

    public MovieServiceImpl(MovieRepository movieRepository) {
        super(movieRepository);
    }
}
