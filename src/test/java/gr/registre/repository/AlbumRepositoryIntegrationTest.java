package gr.registre.repository;

import gr.registre.model.category.AlbumCategory;
import gr.registre.model.medium.Album;
import gr.registre.model.review.AlbumReview;
import gr.registre.repository.medium.AlbumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AlbumRepositoryIntegrationTest implements MediumRepositoryIntegrationTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Before
    public void init() {
        Album a1 = new Album();
        a1.setTitle("A1");
        a1.setArtist("A1 Artist");
        a1.setCategory(AlbumCategory.OTHER);
        a1.setIsCompleted(false);
        a1.setDateReleased(LocalDate.now());
        a1.setDateCompleted(null);
        a1.setRating(5);
        a1.setReview(null);

        Album a2 = new Album();
        a2.setTitle("A2");
        a2.setArtist("A2 Artist");
        a2.setCategory(AlbumCategory.OTHER);
        a2.setIsCompleted(true);
        a2.setDateReleased(LocalDate.now());
        a2.setDateCompleted(LocalDate.now());
        a2.setRating(8);

        AlbumReview r1 = new AlbumReview();
        r1.setText("Review text");
        r1.setDateModified(LocalDate.now());
        r1.setAlbum(a2);

        a2.setReview(r1);

        albumRepository.save(a1);
        albumRepository.save(a2);
    }

    @Test
    @Override
    public void testFindAllWhenMediaExist() {
        List<Album> albums = albumRepository.findAll();
        assertNotNull(albums);
    }

    @Test
    @Override
    public void testFindMediumById() {
        Album album = new Album();
        album.setTitle("Title");
        album.setArtist("Artist");
        albumRepository.save(album);

        Optional<Album> optionalAlbum = albumRepository.findById(album.getId());
        assertNotNull(optionalAlbum.orElse(null));
    }

    @Test
    @Override
    public void testFindMediumByInvalidId() {
        Optional<Album> optionalAlbum = albumRepository.findById(-1);
        assertNull(optionalAlbum.orElse(null));
    }

    @Test
    @Override
    public void testSaveMedium() {
        Album album = new Album();
        album.setTitle("Title");
        album.setArtist("Artist");
        albumRepository.save(album);
        assertNotNull(albumRepository.findById(album.getId()));
    }

    @Test
    @Override
    public void testDeleteMediumById() {
        albumRepository.deleteById(1);
        Optional<Album> optionalAlbum = albumRepository.findById(1);
        assertNull(optionalAlbum.orElse(null));
    }
}
