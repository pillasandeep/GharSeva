package com.example.Webapp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Customer")
public class CustomerEntity {

   
    @Column
    private String name;

    @Id
   
    private String phonenumber;

   
    @Column
    private String password;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String phonenumber, String password) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
