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

    @NotEmpty(message = "university field is required.")
    private String university;


    @NotEmpty(message = "shift field is required.")
    private String shift;


    @Min(value = 0, message = "the trip index must be 0, 1, 2.")
    @Max(value = 2, message = "the trip index must be 0, 1, 2.")
    private int tripIndex;


}
