package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "driver_trips")
public class DriverTrips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "university field is required.")
    private String university;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    private String shift;

    // Relation

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Driver driver;
}
