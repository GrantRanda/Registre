package gr.registre.repository.medium;

import gr.registre.model.medium.Medium;
import gr.registre.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MediumRepository<M extends Medium<R>, R extends Review> extends JpaRepository<M, Integer> {

    List<M> findByIsCompletedTrueOrderByDateCompletedDesc();

    List<M> findByIsCompletedFalseOrderByCategoryAsc();
}
