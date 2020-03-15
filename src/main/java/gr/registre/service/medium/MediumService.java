package gr.registre.service.medium;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediumService<M extends Medium<R>, R extends Review> {

    List<M> findAll();

    List<M> findAllCompleted();

    List<M> findAllUncompleted();

    M findById(int id);

    void save(M medium);

    void deleteById(int id);

    Page<M> findPaginated(Pageable pageable, List<M> media);
}
