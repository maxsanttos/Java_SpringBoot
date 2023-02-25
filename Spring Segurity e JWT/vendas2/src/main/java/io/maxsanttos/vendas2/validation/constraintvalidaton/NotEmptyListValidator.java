package io.maxsanttos.vendas2.validation.constraintvalidaton;

import io.maxsanttos.vendas2.validation.NotEmptyList;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    
    @Override
    public boolean isValid( List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
