package com.alen.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import javax.persistence.Id;

@Entity
@Table(name = "EMP")
public class Employee extends PanacheEntity {
    @Column(name = "firstname")
    public String firstName;

    @Column(name = "lastname")
    public String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    public long id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}