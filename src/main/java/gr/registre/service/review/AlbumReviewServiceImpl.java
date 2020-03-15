package gr.registre.service.review;

import gr.registre.model.review.AlbumReview;
import gr.registre.repository.review.AlbumReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumReviewServiceImpl extends AbstractReviewServiceImpl<AlbumReview> implements AlbumReviewService {

    public AlbumReviewServiceImpl(AlbumReviewRepository albumReviewRepository) {
        super(albumReviewRepository);
    }
}
