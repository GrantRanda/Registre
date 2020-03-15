package gr.registre.service.medium;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import gr.registre.repository.medium.MediumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractMediumServiceImpl<M extends Medium<R>, R extends Review> implements MediumService<M, R> {

    protected MediumRepository<M, R> mediumRepository;

    public AbstractMediumServiceImpl(MediumRepository<M, R> mediumRepository) {
        this.mediumRepository = mediumRepository;
    }

    public List<M> findAll() {
        return mediumRepository.findAll();
    }

    public List<M> findAllCompleted() {
        return mediumRepository.findByIsCompletedTrueOrderByDateCompletedDesc();
    }

    public List<M> findAllUncompleted() {
        return mediumRepository.findByIsCompletedFalseOrderByCategoryAsc();
    }

    public M findById(int id) {
        Optional<M> medium = mediumRepository.findById(id);

        if (!medium.isPresent()) {
            throw new RuntimeException("Unable to find medium with ID: " + id);
        }

        return medium.orElse(null);
    }

    public void save(M medium) {
        mediumRepository.save(medium);
    }

    public void deleteById(int id) {
        mediumRepository.deleteById(id);
    }

    public Page<M> findPaginated(Pageable pageable, List<M> media) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startItem = pageNumber * pageSize;
        List<M> list;

        if (media.size() < startItem) {
            list = Collections.emptyList();
        } else {
            list = media.subList(startItem, Math.min(startItem + pageSize, media.size()));
        }

        return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), media.size());
    }
}
