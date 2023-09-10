package com.example.wayz.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class StudentTripsDTO {

    private Integer day;

    @NotEmpty(message = "type can't be empty")
    @Pattern(message = "the type can only be `going`, `return`.", regexp = "(?i)\\b(going|return)\\b?")
    private String type;

    private LocalDateTime timestamp;
}
