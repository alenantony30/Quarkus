package org.alen;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Employee extends PanacheEntity {
    public String firstname;
    public String lastname;

    public Long id;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Employee(String firstname, String lastname, Long id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    public Employee() {
    }
}
