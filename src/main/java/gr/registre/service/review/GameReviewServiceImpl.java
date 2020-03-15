package gr.registre.service.review;

import gr.registre.model.review.GameReview;
import gr.registre.repository.review.GameReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class GameReviewServiceImpl extends AbstractReviewServiceImpl<GameReview> implements GameReviewService {

    public GameReviewServiceImpl(GameReviewRepository gameReviewRepository) {
        super(gameReviewRepository);
    }
}
