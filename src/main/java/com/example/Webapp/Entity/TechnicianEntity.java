package com.example.Webapp.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "technician")
public class TechnicianEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String phonenumber;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String type;
    
    // One Technician → Many TechBookings
    @OneToMany(mappedBy = "technician", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TechBookingEntity> techBookings;

    public TechnicianEntity() {
        super();
    }

    public TechnicianEntity(String phonenumber, String name, String password, String type) {
        super();
        this.phonenumber = phonenumber;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TechBookingEntity> getTechBookings() {
        return techBookings;
    }

    public void setTechBookings(List<TechBookingEntity> techBookings) {
        this.techBookings = techBookings;
    }
}
