package com.n26.validation;

import java.util.List;

import com.n26.validation.rules.ValidationRule;

public interface Validator {
    Validator withRule(ValidationRule rule);
    List<ValidationRule> listRules();
    void validate();
}
