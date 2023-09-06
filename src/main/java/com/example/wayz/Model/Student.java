package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "the university field is required.")
    @Column(nullable = false)
    private String university;

    @NotEmpty(message = "the home google map URL field is required.")
    @Column(nullable = false)
    private String homeGoogleMapUrl;


    @Column(nullable = false)
    private Integer tripsLeft=0;


    @OneToOne
    @MapsId
    @JsonIgnore // prevent infinite loop
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<StudentTrips> studentTrips;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Orders> order;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Report> Reports;
}
