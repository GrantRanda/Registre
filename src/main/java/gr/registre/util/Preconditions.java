package gr.registre.util;

import gr.registre.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

/**
 * Utility methods to validate resources before performing operations. An {@link HttpStatus}
 * code is thrown if a precondition fails.
 */
public class Preconditions {

    private Preconditions() {
        throw new AssertionError();
    }

    /**
     * Checks if the given resource is null. Returns the resource if it is not null, otherwise
     * throws an exception.
     *
     * @param resource the resource
     * @param <T> the resource type
     * @return the resource if it is not null
     */
    public static <T> T checkNotNull(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
}
