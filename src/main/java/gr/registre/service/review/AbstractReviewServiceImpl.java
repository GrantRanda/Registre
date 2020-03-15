package gr.registre.service.review;

import gr.registre.model.review.Review;
import gr.registre.repository.review.ReviewRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractReviewServiceImpl<R extends Review> implements ReviewService<R> {

    private ReviewRepository<R> reviewRepository;

    public AbstractReviewServiceImpl(ReviewRepository<R> reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<R> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public R findById(int id) {
        Optional<R> review = reviewRepository.findById(id);

        if (!review.isPresent()) {
            throw new RuntimeException("Unable to find review with ID: " + id);
        }

        return review.orElse(null);
    }

    @Override
    public void save(R review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(int id) {
        reviewRepository.deleteById(id);
    }
}
