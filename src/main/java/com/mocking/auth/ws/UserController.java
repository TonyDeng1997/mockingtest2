package com.mocking.auth.ws;

import com.mocking.auth.model.TestForm;
import com.mocking.auth.model.User;
import com.mocking.auth.service.SecurityService;
import com.mocking.auth.service.UserService;
import com.mocking.auth.validator.UserValidator;
import util.RunCode;
import util.SourceCode;
import util.ConfigProperties;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/*@author feifei*/

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping(value = { "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}

	@GetMapping(value = "timer")
	public String getTimer(Model model) {
		model.addAttribute("form", new TestForm());
		return "timer";
	}

	@PostMapping(value = { "/test" })
	public ModelAndView test(@ModelAttribute("form") TestForm form, Model model) throws IOException {
		System.out.println(form.getSource_code());
		ConfigProperties ohla = new ConfigProperties();

		// TODO Fix here, big bug
		SourceCode UserCode = new SourceCode("Hello", form.getSource_code(), "java");

		RunCode TheProcess = new RunCode(UserCode);
		Path hello = TheProcess.executeCode();
		Map<String, Object> params = new HashMap<>();
		FileReader fr;
		try {
			String data = "";
			data = new String(Files.readAllBytes(Paths.get(hello + "/" + "log.txt")));
			System.out.println(data);

			params.put("feedback", data);
			System.out.println("done reading file");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("done");

		return new ModelAndView("timer", params);
	}
}
