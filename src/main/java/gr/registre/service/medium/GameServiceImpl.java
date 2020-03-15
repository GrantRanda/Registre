package gr.registre.service.medium;

import gr.registre.model.medium.Game;
import gr.registre.model.review.GameReview;
import gr.registre.repository.medium.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends AbstractMediumServiceImpl<Game, GameReview> implements GameService {

    public GameServiceImpl(GameRepository gameRepository) {
        super(gameRepository);
    }
}
