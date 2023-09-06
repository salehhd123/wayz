package com.example.wayz.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "the university field is required.")
    @Column(nullable = false)
    private String university;

    @NotEmpty(message = "the home google map URL field is required.")
    @Column(nullable = false)
    private String home_google_map_url;


    @Column(nullable = false)
    private Integer trips_left=0;




}
