package com.example.demo.Entity;

import org.springframework.data.annotation.Reference;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "azimjon_table")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(max = 50,min = 5)
    @Column(length = 50, unique = true, nullable = false)
    private String login;
    @NonNull
    @Size(max = 50,min = 8)
    @Column(length = 50,unique = true,nullable = false)
    private String  password;

    private String firstName;
    private String lastName;
    private String email;
    @NonNull
    @Column(nullable = false)
    public  Boolean activator=false;

    private String langKey;

    @ManyToMany
    @JoinTable (
            name = "user_role",
            joinColumns ={@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")}
    )

    private Set<Role> roles =new HashSet<>();



}
