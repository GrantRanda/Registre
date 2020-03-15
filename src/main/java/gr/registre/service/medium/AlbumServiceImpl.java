package gr.registre.service.medium;

import gr.registre.model.medium.Album;
import gr.registre.model.review.AlbumReview;
import gr.registre.repository.medium.AlbumRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends AbstractMediumServiceImpl<Album, AlbumReview> implements AlbumService {

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        super(albumRepository);
    }
}
