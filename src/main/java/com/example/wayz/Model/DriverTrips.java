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


    @NotNull(message = "day field is required.")
    @Min(value = 1, message = "the day can only be from 1 to 5 where 1 = Sunday, 2 = Monday, 3 = Tuesday etc...")
    @Max(value = 5, message = "the day can only be from 1 to 5 where 1 = Sunday, 2 = Monday, 3 = Tuesday etc...")
    @Column(nullable = false)
    private Integer day;

    @NotEmpty(message = "type field is required.")
    @Pattern(message = "the type can only be `going`, `return`.", regexp = "(?i)\\b(going|return)\\b?")
    @Column(columnDefinition = "varchar(255) not null check (type in ('going', 'return')) ")
    private String type;

    @NotEmpty(message = "university field is required.")
    private String university;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;

    @NotEmpty(message = "pickup location field is required.")
    @Column(nullable = false)
    private String pickupLocation;

    // Relation

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Driver driver;
}
