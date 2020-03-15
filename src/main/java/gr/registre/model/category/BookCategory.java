package gr.registre.model.category;

/**
 * Enum for book categories.
 */
public enum BookCategory implements Category {

    OTHER("Other"),
    ART("Art"),
    BIOGRAPHY("Biography"),
    BUSINESS("Business"),
    CLASSIC("Classic"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    FANTASY("Fantasy"),
    FICTION("Fiction"),
    HISTORICAL_FICTION("Historical Fiction"),
    HISTORY("History"),
    HORROR("Horror"),
    MEMOIR("Memoir"),
    MUSIC("Music"),
    MYSTERY("Mystery"),
    NONFICTION("Nonfiction"),
    PARANORMAL("Paranormal"),
    PHILOSOPHY("Philosophy"),
    POETRY("Poetry"),
    PSYCHOLOGY("Psychology"),
    RELIGION("Religion"),
    ROMANCE("Romance"),
    SCIENCE("Science"),
    SCIENCE_FICTION("Science Fiction"),
    SELF_HELP("Self Help"),
    SUSPENSE("Suspense"),
    SPIRITUALITY("Spirituality"),
    SPORTS("Sports"),
    THRILLER("Thriller"),
    TRAVEL("Travel"),
    YOUNG_ADULT("Young Adult");

    private final String name;

    BookCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
