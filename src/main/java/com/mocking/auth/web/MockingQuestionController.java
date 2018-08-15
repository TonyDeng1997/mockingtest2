package com.mocking.auth.web;


import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.model.User;
import com.mocking.auth.service.MockingConfigService;
import com.mocking.auth.service.MockingQuestionService;
import com.mocking.auth.service.SecurityService;
import com.mocking.auth.service.UserService;
import com.mocking.auth.validator.MockingConfigValidator;
import com.mocking.auth.validator.MockingQuestionValidator;
import com.mocking.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
//
@Controller
public class MockingQuestionController {

    @Autowired
    private MockingQuestionService mockingQuestionService;

    //TODO finish validator
    @Autowired
    private MockingQuestionValidator mockingQuestionValidator;

    //TODO Get 
    @RequestMapping(value = "mocking/questiontest", method = RequestMethod.GET)
    public String getMockingConfig(Model model) {
        model.addAttribute("mockingQuestionForm", new MockingQuestion());

        return "questiontest";
    }

    //TODO, need to create a web form just like userForm
    @RequestMapping(value = "/mocking/questiontest", method = RequestMethod.POST)
    public String saveMockdingConfig(@ModelAttribute("mockingQuestionForm") MockingQuestion mockingQuestionForm, BindingResult bindingResult, Model model) {
        // TODO need to implement a mockingConfigValidator
    	mockingQuestionValidator.validate(mockingQuestionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "questiontest";
        }

        mockingQuestionService.save(mockingQuestionForm);

        return "redirect:/welcome";
    }
  
	
    /*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    */
}