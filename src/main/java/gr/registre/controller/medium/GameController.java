package gr.registre.controller.medium;

import gr.registre.model.medium.Game;
import gr.registre.model.review.GameReview;
import gr.registre.service.medium.GameService;
import gr.registre.service.review.GameReviewService;
import gr.registre.service.review.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/games")
public class GameController extends AbstractMediumController<Game, GameReview> {

    public GameController(GameService gameService, GameReviewService gameReviewService) {
        super(gameService, gameReviewService);
        viewTable = "tables/game-table";
        viewForm = "forms/game-form";
        viewReviews = "reviews/game-reviews";
        viewReviewForm = "forms/game-review-form";
        modelSingular = "game";
        modelPlural = "games";
    }

    @Override
    public String editMedium(int id, Model model, boolean isCompleted) {
        Game game = null;

        if (id > 0) {
            game = mediumService.findById(id);
        }
        if (game == null) {
            game = new Game();

            if (isCompleted) {
                game.setIsCompleted(true);
                game.setDateCompleted(LocalDate.now());
            }
        }
        model.addAttribute(modelSingular, game);

        return viewForm;
    }

    @Override
    public String editReview(int id, Model model) {
        Game game = mediumService.findById(id);

        if (game.getReview() == null) {
            game.setReview(new GameReview());
        }

        model.addAttribute("title", game.getTitle());
        model.addAttribute("review", game.getReview());
        model.addAttribute(modelPlural, mediumService.findAllCompleted());

        return viewReviewForm;
    }
}
