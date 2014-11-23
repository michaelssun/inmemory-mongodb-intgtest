package com.tealium.tutorial.inmemorymongo.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

@Path("/accounts") 
public interface AccountRestResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(JSONObject inputJsonObj);

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("name") String name);

	@DELETE
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("name") String name);

	@PUT
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("name") String name, JSONObject inputJsonObj);

}
