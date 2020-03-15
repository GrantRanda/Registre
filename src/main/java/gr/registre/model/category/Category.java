package gr.registre.model.category;

/**
 * A named category.
 */
public interface Category {

    /**
     * Returns the name of this category.
     *
     * @return the category name
     */
    String getName();

    /**
     * Finds and returns the constant of an enumerated type implementing this interface using
     * the given category name. Used by attribute converters to convert a category name String to
     * the corresponding enumerated constant.
     *
     * @param name  the category name to search for
     * @param type  the concrete enumerated type in which to search
     * @param <T>   the type parameter
     * @return      the enumerated constant with a name field whose value matches the given name
     */
    static <T extends Enum<T> & Category> T fromName(String name, Class<T> type) throws IllegalArgumentException {
        for (T category : type.getEnumConstants()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Category with name \"" + name + "\" was not found.");
    }
}
