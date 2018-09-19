
package com.mocking.auth.ws;

/*@author feifei*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mocking.auth.model.question_data;
import com.mocking.auth.service.QuestionService;


//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
//
@Controller
public class QuestionController {

    @Autowired
    private QuestionService QuestionService;

    
    

    //TODO Get 
    @RequestMapping(value = "submitquestion", method = RequestMethod.GET)
    public String getQuestion(Model model) {
        model.addAttribute("questionForm", new question_data());

        return "submitquestion";
    }
    
    

    

    //TODO, need to create a web form just like userForm
    @RequestMapping(value = "submitquestion", method = RequestMethod.POST)
    public String saveMockdingConfig(@ModelAttribute("questionForm") question_data questionForm, Model model) {
        // TODO need to implement a mockingConfigValidator
    	

       

        QuestionService.save(questionForm);

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
