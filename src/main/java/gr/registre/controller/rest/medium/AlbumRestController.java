package gr.registre.controller.rest.medium;

import gr.registre.model.medium.Album;
import gr.registre.model.review.AlbumReview;
import gr.registre.service.medium.AlbumService;
import gr.registre.service.review.AlbumReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController extends AbstractMediumRestController<Album, AlbumReview> {

    public AlbumRestController(AlbumService albumService, AlbumReviewService albumReviewService) {
        super(albumService, albumReviewService);
    }
}
