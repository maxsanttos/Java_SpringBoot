package io.maxsanttos.vendas2.validation.constraintvalidaton;

import jakarta.validation.ConstraintValidator;
import java.util.List;
import io.maxsanttos.vendas2.validation.NotEmptyList;
import jakarta.validation.ConstraintValidatorContext;


public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    
    @Override
    public boolean isValid( List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
