package com.example.wayz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    String plateLetter;
    String plateNumbers;

    public String getCarPlate() {
        return plateNumbers + "*" + plateLetter;
    }
}
