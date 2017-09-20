package com.freeman.restful.jaxb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.freeman.restful.model.TodoXML;
@Path("/todo")
public class TodoXMLResource {
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public TodoXML getXML() {
	    TodoXML todo = new TodoXML();
	    todo.setSummary("This is my first todo");
	    todo.setDescription("This is my first todo");
	    return todo;
	}
	
	@GET
	@Produces({MediaType.TEXT_XML})
	public TodoXML getHTML() {
	    TodoXML todo = new TodoXML();
	    todo.setSummary("This is my first todo");
	    todo.setDescription("This is my first todo");
	    return todo;
	}
}
