package com.example.wayz.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

    private Integer student_user_id;

    @NotEmpty(message = "the number field is required.")
    private String number;

    @NotEmpty(message = "the name field is required.")
    private String name;

    @NotEmpty(message = "the ccv field is required.")
    private String ccv;




}
