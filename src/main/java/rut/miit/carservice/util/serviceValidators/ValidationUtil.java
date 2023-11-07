package rut.miit.carservice.util.serviceValidators;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

/**
 * todo Document type ValidationUtil
 */
public interface ValidationUtil {
    <E> boolean isValid(E object);

    <E> Set<ConstraintViolation<E>> violations(E object);
}
