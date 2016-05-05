/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.menlopark.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class ValidationErrorContainer {

    private List<ValidationError> validationErrors = new ArrayList<>();

    public void addValidationError(String field, String message) {
        validationErrors.add(new ValidationError(field, message));
    }

    public List<ValidationError> getFieldErrors() {
        return validationErrors;
    }
}
