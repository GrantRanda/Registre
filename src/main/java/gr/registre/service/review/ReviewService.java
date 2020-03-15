package gr.registre.service.review;

import gr.registre.model.review.Review;

import java.util.List;

public interface ReviewService<R extends Review> {

    List<R> findAll();

    R findById(int id);

    void save(R review);

    void deleteById(int id);
}
