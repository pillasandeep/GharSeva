package com.example.Webapp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "Customer")
public class CustomerEntity {

	@NotBlank(message = "Name is required")
    @Size(min = 2, max = 16, message = "Name must be 2–16 characters")
    @Column(length = 50, nullable = false)
    private String name;

    @Id
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Column(length = 10, nullable = false, unique = true)
    private String phonenumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 60, message = "Password must be 6–30 characters")
    @Column(nullable = false)
    private String password;

    @Transient
    private String confirmpassword;

    public CustomerEntity() {}

    public CustomerEntity(String name, String phonenumber, String password, String cp) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = password;
        this.confirmpassword = cp;
    }

   

    public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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
