package com.example.wayz.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "the username field is required.")
    @Column(nullable = false, unique = true)
    private String username;


    @NotEmpty(message = "the password field is required.")
    @Column(nullable = false)
    private String password;


    @Column(columnDefinition = "varchar(40) not null default 'USER'")
    private String role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Driver driver;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<MyFile> files;
}
