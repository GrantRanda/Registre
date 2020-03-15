package gr.registre.controller.medium;

import gr.registre.model.medium.Album;
import gr.registre.model.review.AlbumReview;
import gr.registre.service.medium.AlbumService;
import gr.registre.service.review.AlbumReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/albums")
public class AlbumController extends AbstractMediumController<Album, AlbumReview> {

    public AlbumController(AlbumService albumService, AlbumReviewService albumReviewService) {
        super(albumService, albumReviewService);
        viewTable = "tables/album-table";
        viewForm = "forms/album-form";
        viewReviews = "reviews/album-reviews";
        viewReviewForm = "forms/album-review-form";
        modelSingular = "album";
        modelPlural = "albums";
    }

    @Override
    public String editMedium(int id, Model model, boolean isCompleted) {
        Album album = null;

        if (id > 0) {
            album = mediumService.findById(id);
        }
        if (album == null) {
            album = new Album();

            if (isCompleted) {
                album.setIsCompleted(true);
                album.setDateCompleted(LocalDate.now());
            }
        }
        model.addAttribute(modelSingular, album);

        return viewForm;
    }

    @Override
    public String editReview(int id, Model model) {
        Album album = mediumService.findById(id);

        if (album.getReview() == null) {
            album.setReview(new AlbumReview());
        }

        model.addAttribute("title", album.getTitle());
        model.addAttribute("review", album.getReview());
        model.addAttribute(modelPlural, mediumService.findAllCompleted());

        return viewReviewForm;
    }
}
