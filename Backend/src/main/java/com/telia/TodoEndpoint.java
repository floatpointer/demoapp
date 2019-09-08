package com.telia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telia.db.InMemoryDatabase;
import com.telia.models.Todo;

/**
 * SpringBoot main
 * Simple backend REST service using Spring Boot
 * This application is launched from the terminal using mvn spring-boot:run
 * It is found on localhost:8080 (using default Catalina server)
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.telia")
public class TodoEndpoint {
	
	@Autowired
	InMemoryDatabase database;
	
	public static void main(String... args) {
		SpringApplication.run(TodoEndpoint.class, args);
	}
	
	@RequestMapping("/")
	String home() {
		return "<h2>Demo application Thomas Vennström</h2>";
	}
	
	/**
	 * Should use RequestMethod.DELETE according to spec, but currently I can't get CORS to allow it..
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deletetodo/{id}", method = RequestMethod.GET)
	public ResponseEntity delete(@PathVariable("id") int id) {
		database.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value = "todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> get(@PathVariable("id") int id) {
		
		try {
			return ResponseEntity.ok(database.get(id));
		} catch (Exception e) {
			throw new RuntimeException("Failed extracting " + id + " " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "todos", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAll() {
		
		try {
			return ResponseEntity.ok(database.getAll());
		} catch (Exception e) {
			throw new RuntimeException("Failed extracting all " + e.getMessage());
		}
	}
	
	/**
	 * Made this both insert/update
	 * @param id
	 * @param heading
	 * @param text
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "todos", method = RequestMethod.POST)
	public ResponseEntity add(
			@RequestParam(value = "id")int id, 
			@RequestParam(value = "heading")String heading, 
			@RequestParam(value = "text")String text ) {
		
		try {
			Todo newTodo = new Todo(id, heading, text, null);
			newTodo = database.add(newTodo);
			return ResponseEntity.status(HttpStatus.OK).body(newTodo);
		} catch (Exception e) {
			throw new RuntimeException("Failed saving");
		}
	}
}
