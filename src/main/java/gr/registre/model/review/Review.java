package gr.registre.model.review;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@MappedSuperclass
public class Review {

    private static final String ERROR_MESSAGE = "is required";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 1, message = ERROR_MESSAGE)
    @Column(name = "text", columnDefinition = "LONGTEXT")
    protected String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_modified")
    protected LocalDate dateModified;

    public Review() {

    }

    public Review(String text) {
        setText(text);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        setDateModified(LocalDate.now());
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateModified=" + dateModified +
                '}';
    }
}
