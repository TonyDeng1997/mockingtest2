
package com.mocking.auth.ws;

/*@author feifei*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mocking.auth.model.Question_data;
import com.mocking.auth.service.QuestionService;


//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //TODO test this and add a jsp file.
    //https://leetcode.com/problems/two-sum/description/
    @RequestMapping(value = "problem/{title}/description", method = RequestMethod.GET)
    public String getQuestion(Model model, @PathVariable String title) {
    	Question_data question = questionService.findQuestionDataByTitle(title);
        model.addAttribute("questionForm", question);
        return "show_question"; // show_question.jsp
    }

    //TODO, need to create a web form just like userForm
    @RequestMapping(value = "problems/submit", method = RequestMethod.POST)
    public String saveMockdingConfig(@ModelAttribute("questionForm") Question_data questionForm, Model model) {
        // TODO need to implement a mockingConfigValidator
    	questionService.save(questionForm);
        return "redirect:/welcome";
    }
}