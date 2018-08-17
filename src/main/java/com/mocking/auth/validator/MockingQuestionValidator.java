package com.mocking.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.service.MockingQuestionService;

@Component
public class MockingQuestionValidator implements Validator  {

	@Autowired
    private MockingQuestionService mockingQuestionService;
	
	@Override
    public boolean supports(Class<?> aClass) {
        return MockingQuestionService.class.equals(aClass);
    }

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MockingQuestion config=(MockingQuestion) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mocking_config_id", "NotEmpty");
        if (config.getMocking_config_id()< 0 || config.getMocking_config_id() > 32) {
            errors.rejectValue("mocking_config_id", "Size.userForm.username");
        }
        if (mockingQuestionService.findMockingQuestions(config.getMocking_config_id()) != null) {
            errors.rejectValue("mocking_config_id", "Duplicate.userForm.username");
        }


		
	}
    
}
