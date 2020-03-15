package gr.registre.controller.rest.medium;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import gr.registre.service.medium.MediumService;
import gr.registre.service.review.ReviewService;
import gr.registre.util.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class AbstractMediumRestController<M extends Medium<R>, R extends Review> {

    protected MediumService<M, R> mediumService;

    protected ReviewService<R> reviewService;

    public AbstractMediumRestController(MediumService<M, R> mediumService, ReviewService<R> reviewService) {
        this.mediumService = mediumService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<M> getMedia() {
        return mediumService.findAll();
    }

    @GetMapping("/reviews")
    public List<R> getReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public M getMedium(@PathVariable("id") int id) {
        return Preconditions.checkNotNull(mediumService.findById(id));
    }

    @GetMapping("reviews/{id}")
    public R getReview(@PathVariable("id") int id) {
        return Preconditions.checkNotNull(reviewService.findById(id));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveMedium(@RequestBody M medium) {
        Preconditions.checkNotNull(medium);
        mediumService.save(medium);
    }

    @PostMapping("/reviews/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveReview(@RequestBody R review) {
        Preconditions.checkNotNull(review);
        reviewService.save(review);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedium(@PathVariable("id") int id) {
        mediumService.deleteById(id);
    }

    @DeleteMapping("/reviews/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable("id") int id) {
        reviewService.deleteById(id);
    }
}
