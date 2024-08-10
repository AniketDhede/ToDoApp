package com.in28minutes.springBoot.myFirstWebApp.todo;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
  private TodoService todoService;
  private TodoRepository todoRepository;
  
  public TodoControllerJpa(TodoService todoService,TodoRepository todoRepository) {
	super();
	this.todoRepository=todoRepository;
	this.todoService = todoService;
}

@RequestMapping("list-todos")
  public String ListAllTodos(ModelMap m){
	String username=(String) m.get("name");
	
	  List<Todo> todos=todoRepository.findByUsername(username);
	  m.put("todos", todos);
	  return "ListTodos";
  }
@RequestMapping(value="add-todo",method = RequestMethod.GET)
private String showNewTodoPage(ModelMap m1) {
	String user=(String) m1.get("name");
	Todo todo=new Todo(0, user, "", LocalDate.now().plusYears(1), false);
	m1.put("todo", todo);
	return "todos";
}
@RequestMapping(value = "add-todo",method=RequestMethod.POST)
public String addNewTodo(ModelMap m,@Valid Todo todo,BindingResult result) {
	if(result.hasErrors()) {
	return "todos"; 
	}
	String name=(String) m.get("name");
	todo.setUsername(name);
	todoRepository.save(todo);
	//todoService.addTodo(name, todo.getDescription(), todo.getTargetDate(), false);
	return "redirect:list-todos"; 
    }
@RequestMapping(value="delete-todo")
public String deletetodo(@RequestParam int id) { 
	todoRepository.deleteById(id);
	//todoService.deleteById(id);
	return "redirect:list-todos";
}
@RequestMapping(value="update-todo")
public String showUpdateTodo(@RequestParam int id,ModelMap m) { 
	Todo todo=todoRepository.findById(id).get();

	//Todo todo=todoService.findById(id);
	m.addAttribute("todo", todo);
	return "todos";
}
@RequestMapping(value = "update-todo",method=RequestMethod.POST)
public String UpdateTodo(ModelMap m,@Valid Todo todo,BindingResult result) {
	if(result.hasErrors()) {
	return "todos"; 
	}
	//String name=(String) m.get("name");
	String name=getLoggedUsername(m);
	todo.setUsername(name);
	todoRepository.save(todo);
	//todoService.update(todo);
	return "redirect:list-todos"; 
    }
	
	  private String getLoggedUsername(ModelMap m) { // TODO Auto-generated methods tub 
		  Authentication
	  auth=SecurityContextHolder.getContext().getAuthentication(); return
	  auth.getName(); }
	 



} 
