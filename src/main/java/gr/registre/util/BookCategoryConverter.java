package gr.registre.util;

import gr.registre.model.category.BookCategory;
import gr.registre.model.category.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A converter for converting between BookCategory enumerated constants and database fields.
 */
@Converter
public class BookCategoryConverter implements AttributeConverter<BookCategory, String> {

    /**
     * Converts a BookCategory enumerated constant to a category name String.
     *
     * @param category  the BookCategory enumerated constant
     * @return          the book category name
     */
    @Override
    public String convertToDatabaseColumn(BookCategory category) {
        return category == null ? null : category.getName();
    }

    /**
     * Converts a category name string to a BookCategory enumerated constant.
     *
     * @param name  the book category name
     * @return      the BookCategory enumerated constant
     */
    @Override
    public BookCategory convertToEntityAttribute(String name) {
        return name == null ? null : Category.fromName(name, BookCategory.class);
    }
}
