package com.example.practice.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Integer> {
    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("init...");
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(integer>18){
            return true;
        }
        return false;
    }
}
