package gr.registre.model.medium;

import gr.registre.model.category.GameCategory;
import gr.registre.model.review.BookReview;
import gr.registre.model.review.GameReview;
import gr.registre.util.GameCategoryConverter;
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
 * Represents a game. Each game has an associated {@link GameReview}.
 */
@Entity
@Table(name = "game")
public class Game implements Medium<GameReview> {

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
    @Column(name = "developer")
    private String developer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_released")
    private LocalDate dateReleased;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Convert(converter = GameCategoryConverter.class)
    private GameCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_review_id")
    private GameReview review;

    public Game() {

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

    public boolean getIsCompleted() {
        return isCompleted;
    }

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

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
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
    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    @Override
    public GameReview getReview() {
        return review;
    }

    @Override
    public void setReview(GameReview review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", rating=" + rating +
                ", isCompleted=" + isCompleted +
                ", title='" + title + '\'' +
                ", developer='" + developer + '\'' +
                ", dateReleased=" + dateReleased +
                ", dateCompleted=" + dateCompleted +
                ", category=" + category +
                '}';
    }
}
