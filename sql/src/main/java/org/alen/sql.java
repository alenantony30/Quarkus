package org.alen;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/employee")
public class sql {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<Employee> employees = Employee.listAll();
        return Response.ok(employees).build();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Employee.findByIdOptional(id)
                .map(employee -> Response.ok(employee).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addEmployee(Employee emp){

       Employee.persist(emp);
        if(emp.isPersistent()){
            return Response.created(URI.create("/movies"+emp.id)).build();
        }



        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id")Long id){
        boolean deleted= Employee.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}