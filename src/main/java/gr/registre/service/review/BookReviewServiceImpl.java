package gr.registre.service.review;

import gr.registre.model.review.BookReview;
import gr.registre.repository.review.BookReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReviewServiceImpl extends AbstractReviewServiceImpl<BookReview> implements BookReviewService {

    public BookReviewServiceImpl(BookReviewRepository bookReviewRepository) {
        super(bookReviewRepository);
    }
}
