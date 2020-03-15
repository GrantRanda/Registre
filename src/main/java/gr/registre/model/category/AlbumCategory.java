package gr.registre.model.category;

/**
 * Enum for album categories.
 */
public enum AlbumCategory implements Category {

    OTHER("Other"),
    ACOUSTIC("Acoustic"),
    ALTERNATIVE("Alternative"),
    AMBIENT("Ambient"),
    BLUES("Blues"),
    CLASSICAL("Classical"),
    COUNTRY("Country"),
    ELECTRONIC("Electronic"),
    EXPERIMENTAL("Experimental"),
    FOLK("Folk"),
    FUNK("Funk"),
    HIP_HOP("Hip-hop"),
    JAZZ("Jazz"),
    LATIN("Latin"),
    METAL("Metal"),
    POP("Pop"),
    PUNK("Punk"),
    RAP("Rap"),
    REGGAE("Reggae"),
    R_AND_B("R&B"),
    ROCK("Rock"),
    SOUNDTRACK("Soundtrack"),
    SPOKEN_WORD("Spoken word"),
    WORLD("World");

    private final String name;

    AlbumCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
