package gr.registre.model.medium;

import gr.registre.model.category.Category;
import gr.registre.model.review.Review;

import java.time.LocalDate;

public interface Medium<R extends Review> {

    String getTitle();

    void setTitle(String title);

    boolean getIsCompleted();

    void setIsCompleted(boolean isCompleted);

    LocalDate getDateCompleted();

    void setDateCompleted(LocalDate dateCompleted);

    Category getCategory();

    R getReview();

    void setReview(R review);
}
