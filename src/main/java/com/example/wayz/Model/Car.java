package com.example.wayz.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private Integer id;


    @Column(nullable = false)
    @NotNull(message = "car model year cannot be empty")
    @Min(value = 2010, message = "The minimum year allowed is 2010")
    private Integer modelYear;


    @Column(nullable = false)
    private Integer seats;

    @Column(nullable = false, unique = true)
    private String plate;


    /**
     * Sedan
     * Sport
     * Van
     * Hatchback
     * SUV
     * Minivan
     * Truck
     */
    @NotEmpty(message = "the type field is required.")
    @Pattern(message = "the type can only be `sedan`, `sport`, `van`, `hatchback`, `suv`, `minivan`, `truck`.", regexp = "(?i)\\b(sedan|sport|van|hatchback|suv|minivan|truck)\\b?")
    @Column(columnDefinition = "varchar(255) not null check (type in ('sedan', 'sport', 'van', 'hatchback', 'suv', 'minivan', 'truck'))")
    private String type;


    @OneToOne
    @MapsId
    @JsonIgnore // prevent infinite loop
    private Driver driver;


}
