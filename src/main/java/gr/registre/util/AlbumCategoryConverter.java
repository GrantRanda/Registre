package gr.registre.util;

import gr.registre.model.category.AlbumCategory;
import gr.registre.model.category.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A converter for converting between AlbumCategory enumerated constants and database fields.
 */
@Converter
public class AlbumCategoryConverter implements AttributeConverter<AlbumCategory, String> {

    /**
     * Converts a AlbumCategory enumerated constant to a category name String.
     *
     * @param category the AlbumCategory enumerated constant
     * @return the album category name
     */
    @Override
    public String convertToDatabaseColumn(AlbumCategory category) {
        return category == null ? null : category.getName();
    }

    /**
     * Converts a category name string to a AlbumCategory enumerated constant.
     *
     * @param name the album category name
     * @return the AlbumCategory enumerated constant
     */
    @Override
    public AlbumCategory convertToEntityAttribute(String name) {
        return name == null ? null : Category.fromName(name, AlbumCategory.class);
    }
}
