package com.telia.models;

/**
 * Pojo representing a todo
 */
public class Todo {
	
	private int 	id;
	private String 	heading;
	private String 	text;
	private String 	owner; 
	
	public Todo() {
	}
	
	public Todo(String heading, String text, String owner) {
		this.heading 	= heading;
		this.text 		= text;
		this.owner 		= owner;
		this.id			= -1;
	}
	
	public Todo(int id, String heading, String text, String owner) {
		this.heading 	= heading;
		this.text 		= text;
		this.owner 		= owner;
		this.id			= id;
	}

	
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
