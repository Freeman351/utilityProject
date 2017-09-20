package com.freeman.restful.jaxb;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.freeman.restful.dao.TodoDAO;
import com.freeman.restful.model.TodoCRUD;

public class TodoCRUDResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public TodoCRUDResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public TodoCRUD getTodo() {
		TodoCRUD todo = TodoDAO.instance.getModel().get(id);
	    if(todo==null)
	      throw new RuntimeException("Get: Todo with " + id +  " not found");
	    return todo;
	}	

	@GET
	@Produces(MediaType.TEXT_XML)
	public TodoCRUD getTodoHTML() {
		TodoCRUD todo = TodoDAO.instance.getModel().get(id);
	    if(todo==null)
	      throw new RuntimeException("Get: Todo with " + id +  " not found");
	    return todo;
	}
	  
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<TodoCRUD> todo) {
		TodoCRUD c = todo.getValue();
	    return putAndGetResponse(c);
	}
	  
	@DELETE
	public void deleteTodo() {
		TodoCRUD c = TodoDAO.instance.getModel().remove(id);
	    if(c==null)
	    	throw new RuntimeException("Delete: Todo with " + id +  " not found");
	}
	
	private Response putAndGetResponse(TodoCRUD todo) {
		Response res;
		if(TodoDAO.instance.getModel().containsKey(todo.getId())) {
			res = Response.noContent().build();
	    } else {
	    	res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    TodoDAO.instance.getModel().put(todo.getId(), todo);
		    return res;
	}	
}
