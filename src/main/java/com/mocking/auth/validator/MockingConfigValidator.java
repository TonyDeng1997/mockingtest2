package com.mocking.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.service.MockingConfigService;

@Component
public class MockingConfigValidator implements Validator  {

	@Autowired
    private MockingConfigService mockingConfigService;
	
	@Override
    public boolean supports(Class<?> aClass) {
        return MockingConfig.class.equals(aClass);
    }

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MockingConfig config=(MockingConfig) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "candidate_id", "NotEmpty");
        if (config.getCandidate_id().length() < 6 || config.getCandidate_id().length() > 32) {
            errors.rejectValue("candidate_id", "Size.userForm.username");
        }
        if (mockingConfigService.findMockingConfig(config.getCandidate_id()) != null) {
            errors.rejectValue("candidate_id", "Duplicate.userForm.username");
        }


		
	}
    
}
