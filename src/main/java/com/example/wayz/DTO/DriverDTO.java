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
public class DriverDTO {

    @NotEmpty(message = "the username field is required.")
    private String username;


    @NotEmpty(message = "the password field is required.")
    private String password;


    private String status;

    private String driverLicenceImgPath;

    private String carRegistrationImgPath;

    private String driverImgPath;

    private String govIdImgPath;
}
