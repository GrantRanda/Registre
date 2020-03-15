package gr.registre.model.category;

/**
 * Enum for movie categories.
 */
public enum MovieCategory implements Category {

    OTHER("Other"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    FILM_NOIR("Film Noir"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi"),
    SHORT_FILM("Short Film"),
    SPORT("Sport"),
    SUPERHERO("Superhero"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private final String name;

    MovieCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
