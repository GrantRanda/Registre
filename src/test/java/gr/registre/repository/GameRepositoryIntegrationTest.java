package gr.registre.repository;

import gr.registre.model.category.GameCategory;
import gr.registre.model.medium.Game;
import gr.registre.model.review.GameReview;
import gr.registre.repository.medium.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GameRepositoryIntegrationTest implements MediumRepositoryIntegrationTest {

    @Autowired
    private GameRepository gameRepository;

    @Before
    public void init() {
        Game g1 = new Game();
        g1.setTitle("G1");
        g1.setDeveloper("G1 Developer");
        g1.setCategory(GameCategory.OTHER);
        g1.setIsCompleted(false);
        g1.setDateReleased(LocalDate.now());
        g1.setDateCompleted(null);
        g1.setRating(5);
        g1.setReview(null);

        Game g2 = new Game();
        g2.setTitle("G2");
        g2.setDeveloper("G2 Developer");
        g2.setCategory(GameCategory.OTHER);
        g2.setIsCompleted(true);
        g2.setDateReleased(LocalDate.now());
        g2.setDateCompleted(LocalDate.now());
        g2.setRating(8);

        GameReview r1 = new GameReview();
        r1.setText("Review text");
        r1.setDateModified(LocalDate.now());
        r1.setGame(g2);

        g2.setReview(r1);

        gameRepository.save(g1);
        gameRepository.save(g2);
    }

    @Test
    @Override
    public void testFindAllWhenMediaExist() {
        List<Game> games = gameRepository.findAll();
        assertNotNull(games);
    }

    @Test
    @Override
    public void testFindMediumById() {
        Game game = new Game();
        game.setTitle("Title");
        game.setDeveloper("Developer");
        gameRepository.save(game);

        Optional<Game> optionalGame = gameRepository.findById(game.getId());
        assertNotNull(optionalGame.orElse(null));
    }

    @Test
    @Override
    public void testFindMediumByInvalidId() {
        Optional<Game> optionalGame = gameRepository.findById(-1);
        assertNull(optionalGame.orElse(null));
    }

    @Test
    @Override
    public void testSaveMedium() {
        Game game = new Game();
        game.setTitle("Title");
        game.setDeveloper("Developer");
        gameRepository.save(game);
        assertNotNull(gameRepository.findById(game.getId()));
    }

    @Test
    @Override
    public void testDeleteMediumById() {
        gameRepository.deleteById(1);
        Optional<Game> optionalGame = gameRepository.findById(1);
        assertNull(optionalGame.orElse(null));
    }
}
