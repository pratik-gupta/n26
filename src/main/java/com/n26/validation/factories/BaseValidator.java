package com.n26.validation.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.n26.validation.Validator;
import com.n26.validation.rules.ValidationRule;

public class BaseValidator implements Validator {

    private List<ValidationRule> rules;

    BaseValidator(ValidationRule... rules) {
        this.rules = new ArrayList<>();
        this.rules.addAll(Arrays.asList(rules));
    }

    @Override
    public Validator withRule(ValidationRule rule) {
        this.rules.add(rule);
        return this;
    }

    @Override
    public List<ValidationRule> listRules() {
        return rules;
    }

    @Override
    public void validate() {
        rules.forEach(ValidationRule::apply);
    }

}
