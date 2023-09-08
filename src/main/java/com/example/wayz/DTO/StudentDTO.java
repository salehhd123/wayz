package com.example.wayz.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotEmpty(message = "the username field is required.")
    private String username;


    @NotEmpty(message = "the password field is required.")
    private String password;


    @NotEmpty(message = "the name field is required.")
    private String name;

    @NotEmpty(message = "the university field is required.")
    private String university;

    @NotEmpty(message = "the home google map URL field is required.")
    private String homeGoogleMapUrl;

    private Integer tripsLeft=0;

    private Integer balance=0;

}
