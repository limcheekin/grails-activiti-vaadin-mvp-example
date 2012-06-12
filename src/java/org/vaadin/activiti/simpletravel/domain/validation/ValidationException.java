package org.vaadin.activiti.simpletravel.domain.validation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class ValidationException extends RuntimeException {

    private final HashSet<ConstraintViolation<Object>> violations;

    public ValidationException(Collection<ConstraintViolation<Object>> violations) {
        this.violations = new HashSet<ConstraintViolation<Object>>(violations);
    }

    public Set<ConstraintViolation<Object>> getViolations() {
        return Collections.unmodifiableSet(violations);
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Object> violation : violations) {
            sb.append(violation.getMessage());
            sb.append('\n');
        }
        return sb.toString();
    }    
    
}
