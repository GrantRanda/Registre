package gr.registre.model.medium;

import gr.registre.model.category.MovieCategory;
import gr.registre.model.review.BookReview;
import gr.registre.model.review.MovieReview;
import gr.registre.util.MovieCategoryConverter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Represents a movie. Each movie has an associated {@link MovieReview}.
 */
@Entity
@Table(name = "movie")
public class Movie implements Medium<MovieReview> {

    private static final String ERROR_MESSAGE = "is required";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(1)
    @Max(10)
    @Column(name = "rating")
    private int rating = 1;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_completed", columnDefinition = "BIT(1)")
    private boolean isCompleted;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 1, message = ERROR_MESSAGE)
    @Column(name = "title")
    private String title;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 1, message = ERROR_MESSAGE)
    @Column(name = "director")
    private String director;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_released")
    private LocalDate dateReleased;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Convert(converter = MovieCategoryConverter.class)
    private MovieCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_review_id")
    private MovieReview review;

    public Movie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    @Override
    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    @Override
    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Override
    public MovieCategory getCategory() {
        return category;
    }

    public void setCategory(MovieCategory category) {
        this.category = category;
    }

    @Override
    public MovieReview getReview() {
        return review;
    }

    @Override
    public void setReview(MovieReview review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", rating=" + rating +
                ", isCompleted=" + isCompleted +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", dateReleased=" + dateReleased +
                ", dateCompleted=" + dateCompleted +
                ", category=" + category +
                '}';
    }
}
