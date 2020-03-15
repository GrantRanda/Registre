package gr.registre.model.review;

import gr.registre.model.medium.Album;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "album_review")
public class AlbumReview extends Review {

    @OneToOne(mappedBy = "review", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Album album;

    public AlbumReview() {

    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
