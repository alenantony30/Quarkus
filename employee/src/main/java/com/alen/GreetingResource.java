package com.alen;



import com.alen.entity.Employee;
import com.alen.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;



import java.util.List;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    public List<Employee> getAll() {
        return employeeRepository.listAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Employee employee) {
        employeeRepository.persist(employee);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void update(@PathParam("id") Long id, Employee employee) {
        Employee entity = employeeRepository.findById(id);
        if (entity != null) {
            entity.firstName = employee.firstName;
            entity.lastName = employee.lastName;
        }
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        employeeRepository.deleteById(id);
    }
}
