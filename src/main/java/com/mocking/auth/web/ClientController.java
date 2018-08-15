package com.mocking.auth.web;


import com.mocking.auth.model.Client;
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.model.MockingQuestion;
import com.mocking.auth.model.User;
import com.mocking.auth.service.ClientService;
import com.mocking.auth.service.MockingConfigService;
import com.mocking.auth.service.MockingQuestionService;
import com.mocking.auth.service.SecurityService;
import com.mocking.auth.service.UserService;
import com.mocking.auth.validator.ClientValidator;
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
public class ClientController {

    @Autowired
    private ClientService ClientnService;

    //TODO finish validator
    @Autowired
    private ClientValidator ClientValidator;

    //TODO Get 
    @RequestMapping(value = "mocking/clienttest", method = RequestMethod.GET)
    public String getMockingConfig(Model model) {
        model.addAttribute("mockingClientForm", new Client());

        return "clienttest";
    }

    //TODO, need to create a web form just like userForm
    @RequestMapping(value = "/mocking/clienttest", method = RequestMethod.POST)
    public String saveMockdingConfig(@ModelAttribute("mockingClientForm") Client mockingClientForm, BindingResult bindingResult, Model model) {
        // TODO need to implement a mockingConfigValidator
    	ClientValidator.validate(mockingClientForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "clienttest";
        }

        ClientService.save(mockingClientForm);

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