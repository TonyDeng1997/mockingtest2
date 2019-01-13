package com.mocking.auth.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*@author xiaofeng*/
import com.mocking.auth.model.MockingConfig;
import com.mocking.auth.service.MockingConfigService;
import com.mocking.auth.validator.MockingConfigValidator;


//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
//
@RestController
public class MockingConfigController {

    @Autowired
    private MockingConfigService mockingConfigService;

    //TODO finish validator
    @Autowired
    private MockingConfigValidator mockingConfigValidator;

    //TODO Get 
    @GetMapping(value = "mocking/configtest")
    public String getMockingConfig(Model model) {
        model.addAttribute("mockingConfigForm", new MockingConfig());
        return "configtest";
    }

    //TODO, need to create a web form just like userForm
    @PostMapping(value = "/mocking/configtest")
    public String saveMockdingConfig(@ModelAttribute("mockingConfigForm") MockingConfig mockingConfigForm, BindingResult bindingResult, Model model) {
        // TODO need to implement a mockingConfigValidator
    	mockingConfigValidator.validate(mockingConfigForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "configtest";
        }

        mockingConfigService.save(mockingConfigForm);

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