package com.freeman.restful.jaxb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.freeman.restful.dao.TodoDAO;
import com.freeman.restful.model.TodoCRUD;

@Path(value="/todos")
public class TodosCRUDResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<TodoCRUD> getTodosBrowser() {
	    List<TodoCRUD> todos = new ArrayList<TodoCRUD>();
	    todos.addAll(TodoDAO.instance.getModel().values());
	    return todos; 
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<TodoCRUD> getTodos() {
	    List<TodoCRUD> todos = new ArrayList<TodoCRUD>();
	    todos.addAll(TodoDAO.instance.getModel().values());
	    return todos; 
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
	    int count = TodoDAO.instance.getModel().size();
	    return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("id") String id,
			@FormParam("summary") String summary,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		TodoCRUD todo = new TodoCRUD(id,summary);
	    if (description!=null){
	      todo.setDescription(description);
	    }
	    TodoDAO.instance.getModel().put(id, todo);
	    
	    servletResponse.sendRedirect("../create_todo.html");
	}	

	@Path("{todo}")
	public TodoCRUDResource getTodo(@PathParam("todo") String id) {
	    return new TodoCRUDResource(uriInfo, request, id);
	}	
}
