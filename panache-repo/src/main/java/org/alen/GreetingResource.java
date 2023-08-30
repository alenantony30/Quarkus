package org.alen;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    MovieRepository movieRepository;

    @GET
    public Response getAll() {

        List<Movie> movies = movieRepository.listAll();
        return Response.ok(movies).build();

    }


    @GET
    @Path("{id}")
    public Response getById(@PathParam("id")Long id) {

        return movieRepository.findByIdOptional(id)
                .map(employee -> Response.ok(employee).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addMovie(Movie movie){

        movieRepository.persist(movie);
        if(movieRepository.isPersistent(movie)){
            return Response.created(URI.create("/movies"+movie.id)).build();
        }



        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @Transactional
    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id")Long id){
        boolean deleted= movieRepository.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
