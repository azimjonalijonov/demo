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
    @Size(max = 40,min = 4)
    @Column(length = 50, unique = true, nullable = false)
    private String login;
    @NonNull
    @Size(max = 60,min = 20)
    @Column(length = 60,unique = true,nullable = false)
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public Boolean getActivator() {
        return activator;
    }

    public void setActivator(@NonNull Boolean activator) {
        this.activator = activator;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
