package com.mocking.auth.ws;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.service.MockingQuestionService;
import com.mocking.auth.validator.MockingQuestionValidator;

//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
//
@RestController
public class MockingQuestionController {

    @Autowired
    private MockingQuestionService mockingQuestionService;

    //TODO finish validator
    @Autowired
    private MockingQuestionValidator mockingQuestionValidator;

    //TODO Get 
    @GetMapping(value = "mocking/questiontest")
    public String getMockingConfig(Model model) {
        model.addAttribute("mockingQuestionForm", new MockingQuestion());

        return "questiontest";
    }

    //TODO, need to create a web form just like userForm
    @PostMapping(value = "/mocking/questiontest")
    public String saveMockdingConfig(@ModelAttribute("mockingQuestionForm") MockingQuestion mockingQuestionForm, BindingResult bindingResult, Model model) {
        // TODO need to implement a mockingConfigValidator
    	mockingQuestionValidator.validate(mockingQuestionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "questiontest";
        }

        mockingQuestionService.save(mockingQuestionForm);
        return "redirect:/welcome";
    }
  
	
}