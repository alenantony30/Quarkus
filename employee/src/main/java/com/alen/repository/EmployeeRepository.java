package com.alen.repository;


import com.alen.entity.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

//import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepositoryBase<Employee,Long> {
}
