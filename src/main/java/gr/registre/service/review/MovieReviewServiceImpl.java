package gr.registre.service.review;

import gr.registre.model.review.MovieReview;
import gr.registre.repository.review.MovieReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieReviewServiceImpl extends AbstractReviewServiceImpl<MovieReview> implements MovieReviewService {

    public MovieReviewServiceImpl(MovieReviewRepository movieReviewRepository) {
        super(movieReviewRepository);
    }
}
