package com.n26.validation.factories;

import org.springframework.stereotype.Component;

import com.n26.model.Transactions;
import com.n26.validation.Validator;
import com.n26.validation.rules.ValidateTransactionRule;
/**
 * Validation factory class to define and execute validation rules.
 * @author Pratik
 *
 */
@Component
public class RequestValidatorFactory {

    private Validator baseValidator() {
        return new BaseValidator();
    }
    
	public Validator createTransactionValidator(Transactions transactions,long statisticInterval) {
        return baseValidator()
                .withRule(new ValidateTransactionRule(transactions, statisticInterval));
    }
}
