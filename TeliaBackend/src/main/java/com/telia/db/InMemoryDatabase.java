package com.telia.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.telia.models.Todo;

/**
 * Let's pretend this is an in memory database
 */
@Component("inMemoryDatabase")
public class InMemoryDatabase {
	
	private final List<Todo> todos;
	
	public InMemoryDatabase() {
		todos = new ArrayList<Todo>();
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
	
	public synchronized void add(Todo todo) {
		if (todo == null) {
			throw new RuntimeException("TODO cannot be null");
		}
		
		if (todos.size() == 10) {
			throw new RuntimeException("Database is full");
		}
		todos.add(todo);
	}
	
	public synchronized void delete(int id) {
		// Would normally have some sort of checks
		todos.remove(todos.stream().filter(x -> x.getId() == id).findFirst().get());
	}



	
}
