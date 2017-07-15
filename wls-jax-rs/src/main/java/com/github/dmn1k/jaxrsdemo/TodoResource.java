package com.github.dmn1k.jaxrsdemo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/todos")
@Stateless
public class TodoResource {
    @EJB
    private TodoItems todoItems;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response find(@PathParam("id") long id) {
        return todoItems.find(id)
                .map(v -> Response.ok().entity(v).build())
                .orElse(Response.noContent().build());
    }

}
