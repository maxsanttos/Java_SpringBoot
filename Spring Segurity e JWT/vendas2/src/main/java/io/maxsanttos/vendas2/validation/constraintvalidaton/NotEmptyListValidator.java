package io.maxsanttos.vendas2.validation.constraintvalidaton;

import jakarta.validation.ConstraintValidator;
import io.maxsanttos.vendas2.validation.NotEmptyList;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    
    @Override
    public boolean isValid( List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
