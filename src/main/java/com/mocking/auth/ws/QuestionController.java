
package com.mocking.auth.ws;


import java.util.List;

/*@author feifei*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mocking.auth.model.QuestionData;
import com.mocking.auth.service.QuestionService;


//https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito
//https://dzone.com/articles/using-spring-mvc%E2%80%99s
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //TODO Get 
    @GetMapping(value = "submitquestion")
    public String getQuestion(Model model) {
        model.addAttribute("questionForm", new QuestionData());

        return "submitquestion";
    }
    
    //TODO, need to create a web form just like userForm
    @PostMapping(value = "problems/submit")
    public String saveMockdingConfig(@ModelAttribute("questionForm") QuestionData questionForm, Model model) {
        // TODO need to implement a mockingConfigValidator
    	questionService.save(questionForm);
        return "redirect:/welcome";
    }
    
    /*
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
    }*/
    
    @GetMapping("/problem/all")
    public ResponseEntity<List<QuestionData>> listAllUsers() {
        List<QuestionData> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}