package com.example.wayz.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @NotEmpty(message = "Plate letters cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Please enter only letters")
    String plateLetter;

    @NotEmpty(message = "Plate numbers cannot be empty")
    @Pattern(regexp = "\\d+", message = "Please enter only numbers")
    String plateNumbers;

    @NotNull(message = "Model cannot be empty")
    Integer model;

    @NotNull(message = "Seats cannot be empty")
    private Integer seats;

    @NotEmpty(message = "the type field is required.")
    @Pattern(message = "the type can only be `sedan`, `sport`, `van`, `hatchback`, `suv`, `minivan`, `truck`.", regexp = "(?i)\\b(sedan|sport|van|hatchback|suv|minivan|truck)\\b?")
    private String type;


    public String getCarPlate() {
        //// this to split the car plate into two parts no matter how long the plate is we just split by *
        return this.plateNumbers + "*" + this.plateLetter;
    }
}
