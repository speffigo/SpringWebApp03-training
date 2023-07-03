package com.springweb.todoweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springweb.todoweb.service.LogIn;
import com.springweb.todoweb.service.ToDoService;
import com.springweb.todoweb.todo.ToDo;

@Controller
public class LogInController {

	private LogIn logIn;
	private ToDoService services;

	public LogInController(LogIn logIn, ToDoService services) {
		super();
		this.logIn = logIn;
		this.services = services;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String getToDo(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if (logIn.checkAuthentication(name, password)) {
			List<ToDo> todos = services.retrievetodo("saurabh");
			model.put("todos", todos);
			return "todolist";
		}
		return "login";
	}
}
