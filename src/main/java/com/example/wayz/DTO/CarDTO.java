package com.example.wayz.DTO;

public class CarDTO {

    String plateLetter;
    String plateNumbers;

    public String getCarPlate() {
        return plateNumbers + "*" + plateLetter;
    }
}
