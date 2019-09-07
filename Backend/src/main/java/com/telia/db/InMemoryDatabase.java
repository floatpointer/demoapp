package com.telia.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.telia.models.Todo;

/**
 * Let's pretend this is an in memory database
 */
@Component("inMemoryDatabase")
public class InMemoryDatabase {
	
	private final List<Todo> todos;
	
	public InMemoryDatabase() {
		
		/* Just to have some initial data */
		todos = new ArrayList<Todo>();
		Todo existing = new Todo("Sample", "Only for dev", null);
		existing.setId(1);
		todos.add(existing);
		
		existing = new Todo("Sample2", "Only for dev", null);
		existing.setId(2);
		todos.add(existing);

	}

	/**
	 * Would normally return an Optional...
	 * @param id
	 * @return
	 */
	public synchronized Todo get(int id) {
		
		return new Todo("TestA", "TestB", "TestC");
		
//		return todos.stream().filter(x -> x.getId() == id).findFirst();
	}
	
	public List<Todo> getAll() {
		return todos;
	}
	
	public synchronized Todo add(Todo todo) {
		if (todo == null) {
			throw new RuntimeException("TODO cannot be null");
		}
		
		if (todos.size() == 10) {
			throw new RuntimeException("Database is full");
		}
		
		todo.setId(getMaxIdAndThenSome());
		todos.add(todo);
		return todo;
	}
	
	private int getMaxIdAndThenSome() {
		if(todos != null && todos.size() > 0) {
			return this.todos.stream().mapToInt(x -> x.getId()).max().getAsInt() + 1;
		} else {
			return 1;
		}
	}
	
	public synchronized void delete(int id) {
		Optional<Todo> todo = todos.stream().filter(x -> x.getId() == id).findFirst();
		if(todo.isPresent()) {
			todos.remove(todo.get());
		} else {
			throw new RuntimeException("Todo with ID " + id + " not found, delete failed");
		}
	}



	
}
