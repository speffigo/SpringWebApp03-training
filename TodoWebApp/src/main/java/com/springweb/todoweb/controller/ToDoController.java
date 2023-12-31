package com.springweb.todoweb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springweb.todoweb.service.ToDoService;
import com.springweb.todoweb.todo.ToDo;

@Controller
public class ToDoController {

	private ToDoService services;

	public ToDoController(ToDoService services) {
		super();
		this.services = services;
	}

	@RequestMapping("main-todo")
	public String myToDo(ModelMap model) {
		List<ToDo> todos = services.retrievetodo("saurabh");
		model.put("todos", todos);
		return "todolist";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String getToDo(ModelMap model) {
		String username = (String) model.get("name");
		ToDo toDo = new ToDo(username, 0, "", LocalDate.now().plusYears(1), false);
		model.put("todo", toDo);
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String getmyToDo(ModelMap model, ToDo todo) {
		String username = (String) model.get("name");
		services.addToDo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
		return "redirect:main-todo";
	}

	@RequestMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
		services.deleteById(id);
		return "redirect:main-todo";
	}

	@RequestMapping("update-todo")
	public String updateTodoList(@RequestParam int id, ModelMap model) {
		ToDo todo = services.findById(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, ToDo todo) {
		String username = (String) model.get("name");
		services.updateTodo(todo);
		return "redirect:main-todo";
	}

}
