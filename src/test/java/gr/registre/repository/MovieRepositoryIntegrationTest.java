package gr.registre.repository;

import gr.registre.model.category.MovieCategory;
import gr.registre.model.medium.Movie;
import gr.registre.model.review.MovieReview;
import gr.registre.repository.medium.MovieRepository;
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
public class MovieRepositoryIntegrationTest implements MediumRepositoryIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void init() {
        Movie m1 = new Movie();
        m1.setTitle("M1");
        m1.setDirector("M1 Director");
        m1.setCategory(MovieCategory.OTHER);
        m1.setIsCompleted(false);
        m1.setDateReleased(LocalDate.now());
        m1.setDateCompleted(null);
        m1.setRating(5);
        m1.setReview(null);

        Movie m2 = new Movie();
        m2.setTitle("M2");
        m2.setDirector("M2 Director");
        m2.setCategory(MovieCategory.OTHER);
        m2.setIsCompleted(true);
        m2.setDateReleased(LocalDate.now());
        m2.setDateCompleted(LocalDate.now());
        m2.setRating(8);

        MovieReview r1 = new MovieReview();
        r1.setText("Review text");
        r1.setDateModified(LocalDate.now());
        r1.setMovie(m2);

        m2.setReview(r1);

        movieRepository.save(m1);
        movieRepository.save(m2);
    }

    @Test
    @Override
    public void testFindAllWhenMediaExist() {
        List<Movie> movies = movieRepository.findAll();
        assertNotNull(movies);
    }

    @Test
    @Override
    public void testFindMediumById() {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDirector("Director");
        movieRepository.save(movie);

        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());
        assertNotNull(optionalMovie.orElse(null));
    }

    @Test
    @Override
    public void testFindMediumByInvalidId() {
        Optional<Movie> optionalMovie = movieRepository.findById(-1);
        assertNull(optionalMovie.orElse(null));
    }

    @Test
    @Override
    public void testSaveMedium() {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDirector("Director");
        movieRepository.save(movie);
        assertNotNull(movieRepository.findById(movie.getId()));
    }

    @Test
    @Override
    public void testDeleteMediumById() {
        movieRepository.deleteById(1);
        Optional<Movie> optionalMovie = movieRepository.findById(1);
        assertNull(optionalMovie.orElse(null));
    }
}
