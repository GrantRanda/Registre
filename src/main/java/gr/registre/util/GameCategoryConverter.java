package gr.registre.util;

import gr.registre.model.category.Category;
import gr.registre.model.category.GameCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A converter for converting between GameCategory enumerated constants and database fields.
 */
@Converter
public class GameCategoryConverter implements AttributeConverter<GameCategory, String> {

    /**
     * Converts a GameCategory enumerated constant to a category name String.
     *
     * @param category  the GameCategory enumerated constant
     * @return          the game category name
     */
    @Override
    public String convertToDatabaseColumn(GameCategory category) {
        return category == null ? null : category.getName();
    }

    /**
     * Converts a category name string to a GameCategory enumerated constant.
     *
     * @param name  the game category name
     * @return      the GameCategory enumerated constant
     */
    @Override
    public GameCategory convertToEntityAttribute(String name) {
        return name == null ? null : Category.fromName(name, GameCategory.class);
    }
}
