package gr.registre.controller.rest.medium;

import gr.registre.model.medium.Game;
import gr.registre.model.review.GameReview;
import gr.registre.service.medium.GameService;
import gr.registre.service.review.GameReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameRestController extends AbstractMediumRestController<Game, GameReview> {

    public GameRestController(GameService gameService, GameReviewService gameReviewService) {
        super(gameService, gameReviewService);
    }
}
