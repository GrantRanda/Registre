package gr.registre.model.review;

import gr.registre.model.medium.Movie;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_review")
public class MovieReview extends Review {

    @OneToOne(mappedBy = "review", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Movie movie;

    public MovieReview() {

    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
