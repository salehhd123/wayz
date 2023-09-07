package com.example.wayz.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverTripDTO {

    @NotNull(message = "day field is required.")
    @Min(value = 1, message = "the day can only be from 1 to 5 where 1 = Sunday, 2 = Monday, 3 = Tuesday etc...")
    @Max(value = 5, message = "the day can only be from 1 to 5 where 1 = Sunday, 2 = Monday, 3 = Tuesday etc...")
    private Integer day;


    @NotEmpty(message = "type field is required.")
    @Pattern(message = "the type can only be `going`, `return`.", regexp = "(?i)\\b(going|return)\\b?")
    private String type;


    @NotEmpty(message = "university field is required.")
    private String university;

    private LocalDateTime time;

    @NotEmpty(message = "pickup location field is required.")
    private String pickupLocation;

}
