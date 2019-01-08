
package com.mocking.auth.ws;


import java.util.HashMap;
import java.util.Map;

/*@author feifei*/
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mocking.auth.model.QuestionData;
import com.mocking.auth.service.QuestionService;


//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //TODO Get 
    @GetMapping(value = "submitquestion")
    public String getQuestion(Model model) {
        model.addAttribute("questionForm", new QuestionData());

        return "submitquestion";
    }
    
    //TODO test this and add a jsp file.
    //https://leetcode.com/problems/two-sum/description/
    @GetMapping(value = "problem/{title}")
    public ModelAndView getQuestion(Model model, @PathVariable String title) {
    	QuestionData question = questionService.findQuestionDataByTitle(title);
        model.addAttribute("questionForm", new QuestionData());
        Map<String, Object> params = new HashMap<>();
        question.setDescription(new String(Base64.decodeBase64(question.getDescription())));
        params.put("question", question.getDescription());
        params.put("title", question.getTitle());
        return new ModelAndView("show_question", params); // show_question.jsp
    }

    //TODO, need to create a web form just like userForm
    @PostMapping(value = "problems/submit")
    public String saveMockdingConfig(@ModelAttribute("questionForm") QuestionData questionForm, Model model) {
        // TODO need to implement a mockingConfigValidator
    	questionService.save(questionForm);
        return "redirect:/welcome";
    }
}