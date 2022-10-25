package com.example.demo.Entity;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name="devstudent")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String last_name;
     @NotNull
     @Email
    @Size(min = 7,max = 150)
    @Column(length = 150,unique = true,nullable = false)
    private String email;
    @ManyToOne
    private Department department;
   @OneToOne(optional = false)
   @JoinColumn(name ="account_id", unique = true,nullable = false)
    private Account account;

   @OneToMany
   private Set<Item> items;

   @ManyToMany
   @JoinTable(

           name = "dev_employee_project",
           joinColumns={@JoinColumn(name="employee_id" , referencedColumnName = "id")},
           inverseJoinColumns={@JoinColumn(name="project_id" , referencedColumnName = "id")}
   )
   private Set<Project> projects;

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
