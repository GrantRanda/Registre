package gr.registre.controller.medium;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import gr.registre.service.medium.MediumService;
import gr.registre.service.review.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractMediumController<M extends Medium<R>, R extends Review> {

    protected String viewTable;

    protected String viewForm;

    protected String viewReviews;

    protected String viewReviewForm;

    protected String modelSingular;

    protected String modelPlural;

    protected MediumService<M, R> mediumService;

    protected ReviewService<R> reviewService;

    public AbstractMediumController(MediumService<M, R> mediumService, ReviewService<R> reviewService) {
        this.mediumService = mediumService;
        this.reviewService = reviewService;
    }

    @GetMapping("/completed")
    public String mediaCompleted(Model model, @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {

        int pageNumber = page.orElse(1);
        int pageSize = size.orElse(50);
        List<M> media = mediumService.findAllCompleted();

        addPageAttributes(pageNumber, pageSize, media, model);
        model.addAttribute("isCompleted", true);

        return viewTable;
    }

    @GetMapping("/uncompleted")
    public String mediaUncompleted(Model model, @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size) {

        int pageNumber = page.orElse(1);
        int pageSize = size.orElse(50);
        List<M> media = mediumService.findAllUncompleted();

        addPageAttributes(pageNumber, pageSize, media, model);
        model.addAttribute("isCompleted", false);

        return viewTable;
    }

    @PostMapping("/save")
    public String saveMedium(@Valid M medium, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return viewForm;
        }
        mediumService.save(medium);

        return medium.getIsCompleted() ? "redirect:/" + modelPlural + "/completed" : "redirect:/" + modelPlural + "/uncompleted";
    }

    @GetMapping("/delete")
    public String deleteMedium(int id) {
        boolean isCompleted = mediumService.findById(id).getIsCompleted();
        mediumService.deleteById(id);

        return isCompleted ? "redirect:/" + modelPlural + "/completed" : "redirect:/" + modelPlural + "/uncompleted";
    }

    @GetMapping("/reviews")
    public String mediaReviews(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        int pageNumber = page.orElse(1);
        int pageSize = size.orElse(50);
        List<M> media = mediumService.findAllCompleted();

        addPageAttributes(pageNumber, pageSize, media, model);
        model.addAttribute(modelPlural, mediumService.findAllCompleted());

        return viewReviews;
    }

    @GetMapping("/completed/add")
    public String addMediumCompleted(Model model) {
        return editMedium(-1, model, true);
    }

    @GetMapping("/uncompleted/add")
    public String addMediumUncompleted(Model model) {
        return editMedium(-1, model, false);
    }

    @PostMapping("/reviews/save")
    public String saveReview(@Valid @ModelAttribute("review") R review, BindingResult bindingResult,
                             @RequestParam("id") int id, Model model) {

        M medium = mediumService.findById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", medium.getTitle());
            return viewReviewForm;
        }

        medium.setReview(review);
        mediumService.save(medium);

        return "redirect:/" + modelPlural + "/reviews";
    }

    @GetMapping("/reviews/delete")
    public String deleteReview(@RequestParam("id") int id) {
        M medium = mediumService.findById(id);
        int reviewId = medium.getReview().getId();

        medium.setReview(null);
        reviewService.deleteById(reviewId);

        return "redirect:/" + modelPlural + "/reviews";
    }

    /**
     * Marks a book as read. Returns the book form path and sets the date completed field
     * to the current date.
     *
     * @param id    the ID of the book to mark read
     * @param model the model
     * @return the path of the book form
     */
    @GetMapping("/uncompleted/mark-completed")
    public String markCompleted(@RequestParam("id") int id, Model model) {
        M medium = mediumService.findById(id);
        medium.setIsCompleted(true);
        medium.setDateCompleted(LocalDate.now());

        model.addAttribute(modelSingular, medium);

        return viewForm;
    }

    /**
     * Adds a Page and page numbers as attributes to the given Model to be used in pagination.
     *
     * @param pageNumber the current page number
     * @param pageSize   the current page size. That is, the number of table items displayed
     * @param media      the media to be displayed
     * @param model      the model
     */
    public void addPageAttributes(int pageNumber, int pageSize, List<M> media, Model model) {
        Page<M> page = mediumService.findPaginated(PageRequest.of(pageNumber - 1, pageSize), media);

        model.addAttribute("page", page);

        // Calculate the range of page numbers to display in pagination
        int totalPages = page.getTotalPages();
        int number = page.getNumber();
        int currentPage = number + 3;
        int startPage = number > totalPages - 5 ? Math.max(1, totalPages - 8) : Math.max(1, currentPage - 6);
        int endPage = Math.min(startPage + 8, totalPages);

        if (endPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @GetMapping("/edit")
    public abstract String editMedium(@RequestParam("id") int id, Model model, boolean isCompleted);

    @GetMapping("/review")
    public abstract String editReview(@RequestParam("id") int id, Model model);
}
