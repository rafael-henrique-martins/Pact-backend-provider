package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repo.TaskRepo;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

	private TaskRepo todoRepo = new TaskRepo();
	
	@GetMapping
	public List<Task> findAll() {
		return todoRepo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody Task todo) throws ValidationException {
		if(todo.getTask() == null || todo.getTask() == "") {
			throw new ValidationException("Fill the task description");
		}
		if(todo.getDueDate() == null) {
			throw new ValidationException("Fill the due date");
		}
		if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
			throw new ValidationException("Due date must not be in past");
		}
		Task saved = todoRepo.save(todo);
		return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
	}
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task todo) throws ValidationException {
		if(todo.getTask() == null || todo.getTask() == "") {
			throw new ValidationException("Fill the task description");
		}
		if(todo.getDueDate() == null) {
			throw new ValidationException("Fill the due date");
		}
		Task updated = todoRepo.update(new Task(id, todo.getTask(), todo.getDueDate()));
		return new ResponseEntity<Task>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		todoRepo.deleteById(id);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Task> getById(@PathVariable Long id) {
		return new ResponseEntity<Task>(todoRepo.get(id), HttpStatus.OK);
	}
}
