package org.alen;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GreetingResourceTest {

    @InjectMock
    MovieRepository movieRepository;

    @Inject
    GreetingResource greetingResource;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setDirector("Test Director");
        movie.setTitle("Test Title");
        movie.setId(1000L);
    }

    @Test
    void getAll() {
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        Mockito.when(movieRepository.listAll()).thenReturn(movies);

        Response response = greetingResource.getAll();
        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        List<Movie> entity = (List<Movie>) response.getEntity();

        assertFalse(entity.isEmpty());
        assertEquals("Test Director", entity.get(0).getDirector());
        assertEquals("Test Title", entity.get(0).getTitle());
        assertEquals(1000L, entity.get(0).getId());


    }

    @Test
    void getByIdOK() {

        Mockito.when(movieRepository.findByIdOptional(1000L)).thenReturn(Optional.of(movie));
        Response response = greetingResource.getById(1000L);
        Movie entity = (Movie) response.getEntity();

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());

        assertEquals("Test Director", entity.getDirector());
        assertEquals("Test Title", entity.getTitle());
        assertEquals(1000L, entity.getId());


    }

    @Test
    void getByIDNotOK() {

        Mockito.when(movieRepository.findByIdOptional(1000L)).thenReturn(Optional.empty());
        Response response = greetingResource.getById(1000L);

        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        assertNull(response.getEntity());

    }

    @Test
    void addMovieOK() {

        Mockito.doNothing().when(movieRepository).persist(ArgumentMatchers.any(Movie.class));

        Mockito.when(movieRepository.isPersistent(ArgumentMatchers.any(Movie.class))).thenReturn(true);

        Movie newMovie = new Movie();
        newMovie.setTitle("New Title");
        newMovie.setDirector("New Director");
        Response response = greetingResource.addMovie(newMovie);
        assertNotNull(response);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

        assertNotNull(response.getLocation());
    }

    @Test
    void addMovieNotOK() {

        Mockito.doNothing().when(movieRepository).persist(ArgumentMatchers.any(Movie.class));

        Mockito.when(movieRepository.isPersistent(ArgumentMatchers.any(Movie.class))).thenReturn(false);

        Movie newMovie = new Movie();
        newMovie.setTitle("New Title");
        newMovie.setDirector("New Director");
        Response response = greetingResource.addMovie(newMovie);
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());

        assertNull(response.getEntity());
    }

    @Test
    void deleteByIdOK() {

        Mockito.when(movieRepository.deleteById(1000L)).thenReturn(true);
        Response response = greetingResource.deleteById(1000L);
        assertNotNull(response);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(),response.getStatus());
        assertNull(response.getEntity());



    }

    @Test
    void deleteByIdNotOK() {

        Mockito.when(movieRepository.deleteById(1000L)).thenReturn(false);
        Response response = greetingResource.deleteById(1000L);
        assertNotNull(response);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),response.getStatus());
        assertNull(response.getEntity());



    }
}