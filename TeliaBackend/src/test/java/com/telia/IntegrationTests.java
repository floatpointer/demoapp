package com.telia;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telia.db.InMemoryDatabase;
import com.telia.models.Todo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

/**
 * Integrations tests for the backend of the Telia demo app
 * Usage: mvn test
 */
@RunWith(SpringRunner.class)
public class IntegrationTests {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@TestConfiguration
    static class TeliaBackendContextConfiguration {
  
        @Bean
        public TeliaBackend backend() {
            return new TeliaBackend();
        }
    }
	
	@Autowired
	private TeliaBackend backend;
	
	@MockBean
	private InMemoryDatabase database;
	
	private Todo todo1;
	private List<Todo> theList;
	
	@Before
	public void setUp() {
		todo1 = new Todo("header", "a text", "user");
		theList = new ArrayList<Todo>();
		theList.add(todo1);
		when(database.get(anyInt())).thenReturn(todo1);
		when(database.getAll()).thenReturn(theList);
	}
	
	@Test
	public void testGetTodo() {
		ResponseEntity<Todo> entity = backend.get(1);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("header", entity.getBody().getHeading());
	}

	@Test
	public void testGetAll() {
		ResponseEntity<List<Todo>> entity = backend.getAll();
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(1, entity.getBody().size());
	}
}
