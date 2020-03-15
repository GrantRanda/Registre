package gr.registre.model.medium;

import gr.registre.model.category.AlbumCategory;
import gr.registre.model.review.AlbumReview;
import gr.registre.model.review.BookReview;
import gr.registre.util.AlbumCategoryConverter;
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
 * Represents an album. Each album has an associated {@link AlbumReview}.
 */
@Entity
@Table(name = "album")
public class Album implements Medium<AlbumReview> {

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
    @Column(name = "artist")
    private String artist;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_released")
    private LocalDate dateReleased;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Convert(converter = AlbumCategoryConverter.class)
    private AlbumCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_review_id")
    private AlbumReview review;

    public Album() {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public AlbumCategory getCategory() {
        return category;
    }

    public void setCategory(AlbumCategory category) {
        this.category = category;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public AlbumReview getReview() {
        return review;
    }

    public void setReview(AlbumReview review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", rating=" + rating +
                ", isListened=" + isCompleted +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", dateReleased=" + dateReleased +
                ", dateListened=" + dateCompleted +
                ", category=" + category +
                '}';
    }
}
