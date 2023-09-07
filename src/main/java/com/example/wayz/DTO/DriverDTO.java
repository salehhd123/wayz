package com.example.wayz.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    @NotEmpty(message = "the username field is required.")
    private String username;


    @NotEmpty(message = "the password field is required.")
    private String password;

    @Pattern(message = "the status must be one of `pending`, `approved`, `closed`. ", regexp = "(?i)\\b(pending|approved|closed)\\b?")
    private String status;

    private String driverLicenceImgPath;

    private String carRegistrationImgPath;

    private String driverImgPath;

    private String govIdImgPath;
}
