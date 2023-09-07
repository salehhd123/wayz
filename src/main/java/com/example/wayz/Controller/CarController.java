package com.example.wayz.Controller;

import com.example.wayz.DTO.CarDTO;
import com.example.wayz.Model.Orders;
import com.example.wayz.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/get-all")
    public ResponseEntity getAllCars() {

        return ResponseEntity.status(200).body(carService.getAllCars());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCar(@PathVariable Integer id, @RequestBody @Valid CarDTO carUpdate) {

        carService.updateCar(id, carUpdate);
        return ResponseEntity.status(200).body("Car updated successfully");

    }


}
