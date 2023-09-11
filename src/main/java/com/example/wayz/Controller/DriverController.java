package com.example.wayz.Controller;


import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.User;
import com.example.wayz.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;


    // * for admins only *//
    @GetMapping("/get-all")
    public ResponseEntity getAllDrivers() {

        return ResponseEntity.status(200).body(driverService.getAllDrivers());
    }

    @GetMapping("/get-top")
    public ResponseEntity getTopThreeDrivers() {

        return ResponseEntity.status(200).body(driverService.getTopThreeDrivers());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getDriverById(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(driverService.getDriver(id));
    }


    @PutMapping("/update/{updatedName}")
    public ResponseEntity updateDriverName(@AuthenticationPrincipal User user, @PathVariable String updatedName) {
        driverService.updateDriverName(user.getId(), updatedName);
        return ResponseEntity.status(200).body("Driver name updated successfully");
    }

    // * for admins only *//
    @PutMapping("/approve/{driverId}")
    public ResponseEntity approveDriver(@PathVariable Integer driverId) {
        driverService.approveDriver(driverId);
        return ResponseEntity.status(200).body("Driver approved to work successfully");
    }


    // * for admins only *//
    @PutMapping("/close/{driverId}")
    public ResponseEntity closeDriver(@PathVariable Integer driverId) {
        driverService.closeDriver(driverId);
        return ResponseEntity.status(200).body("Driver fired successfully");
    }
}
