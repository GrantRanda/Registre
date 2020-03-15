package gr.registre.util;

import gr.registre.model.category.Category;
import gr.registre.model.category.MovieCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A converter for converting between MovieCategory enumerated constants and database fields.
 */
@Converter
public class MovieCategoryConverter implements AttributeConverter<MovieCategory, String> {

    /**
     * Converts a MovieCategory enumerated constant to a category name String.
     *
     * @param category  the MovieCategory enumerated constant
     * @return          the movie category name
     */
    @Override
    public String convertToDatabaseColumn(MovieCategory category) {
        return category == null ? null : category.getName();
    }

    /**
     * Converts a category name string to a MovieCategory enumerated constant.
     *
     * @param name  the movie category name
     * @return      the MovieCategory enumerated constant
     */
    @Override
    public MovieCategory convertToEntityAttribute(String name) {
        return name == null ? null : Category.fromName(name, MovieCategory.class);
    }
}
