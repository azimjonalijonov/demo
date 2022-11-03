package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @Id
    @NotNull
    private String name;
}
