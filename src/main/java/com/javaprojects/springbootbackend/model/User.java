package com.javaprojects.springbootbackend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @NotBlank(message = "First name is required!")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Column(name = "last_name")
    private  String lastName;

    @NotBlank(message = "Email is Required!")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "password field is required!")
    @Column(name = "password")
    private String password;

    //add address_id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_add_id")
    private UserAddress address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_place_id", referencedColumnName = "id")
    private List<UserLocation> location;
}
