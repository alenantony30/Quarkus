package com.alen;

import com.alen.entity.Books;
import com.alen.service.BookService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/library")
public class ExampleResource {

    BookService bookService=new BookService();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Books> getAllBooks() {
    return bookService.getAll();
    }

    @GET
    @Path("/getbyid/{id1}")
    @Produces(MediaType.APPLICATION_JSON)
    public Books getByID(@PathParam("id1") int id1) {
        return bookService.getByID(id1);
    }
}
