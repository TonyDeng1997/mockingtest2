package com.mocking.auth.ws;

import com.mocking.auth.model.User;
import com.mocking.auth.service.SecurityService;
import com.mocking.auth.service.UserService;
import com.mocking.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*	@author feifei
 *
 * */

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
	public String registration(@ModelAttribute("userForm") User userForm, 
			BindingResult bindingResult, Model model) {
		
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		return "redirect:/";
	}

	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

	/*
	@GetMapping(value = "/error")
	public String error(Model model) {
		return "login";
	}*/
	
	@GetMapping(value = { "/", "/welcome" })
	public String indexPage(Model model) {
		// The loginName seems to be anonymous during /registration,
		// also also since there is autologin after registration so
		// we want to do it here for now.
		createAndSaveUserHomePath();
		return "index";
	}

	private void createAndSaveUserHomePath() {
		String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
		String homePath = userService.createWorkingDirectory(userService.generateUserHomePath(loginName));
		com.mocking.auth.model.User user = userService.findByUsername(loginName); 
		user.setHomePath(homePath);
		userService.save(user);
	}
}
