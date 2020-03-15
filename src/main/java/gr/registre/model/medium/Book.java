package gr.registre.model.medium;

import gr.registre.model.category.BookCategory;
import gr.registre.model.review.BookReview;
import gr.registre.util.BookCategoryConverter;
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
 * Represents a book. Each book has an associated {@link BookReview}.
 */
@Entity
@Table(name = "book")
public class Book implements Medium<BookReview> {

    private static final String ERROR_MESSAGE = "is required";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "page_count")
    private int pageCount;

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
    @Column(name = "author")
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_started")
    private LocalDate dateStarted;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Convert(converter = BookCategoryConverter.class)
    private BookCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_review_id")
    private BookReview review;

    public Book() {

    }

    public Book(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
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
    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
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
    public BookReview getReview() {
        return review;
    }

    @Override
    public void setReview(BookReview review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", pageCount=" + pageCount +
                ", rating=" + rating +
                ", isCompleted=" + isCompleted +
                ", title='" + title + '\'' +
                ", dateStarted=" + dateStarted +
                ", dateCompleted=" + dateCompleted +
                ", category=" + category +
                '}';
    }
}
