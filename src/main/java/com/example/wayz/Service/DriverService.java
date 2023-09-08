package com.example.wayz.Service;


import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Orders;
import com.example.wayz.Repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;


    // * for admins only *//
    public List<Driver> getAllDrivers() {

        return driverRepository.findAll();
    }

    public Driver getDriver(Integer driverId) {

        Driver driver = driverRepository.findDriverById(driverId);

        if (driver == null) {
            throw new ApiException("Could not find this driver");
        }
        return driver;

    }

    public Driver getTopThreeDrivers() {
        //// TODO
        return new Driver();

    }


    public void updateDriverName(Integer driverId, String nameUpdate) {

        Driver driver = driverRepository.findDriverById(driverId);

        driver.setName(nameUpdate);
        driverRepository.save(driver);
    }

    public void approveDriver(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);

        driver.setStatus("approved");
        driverRepository.save(driver);
    }

    public void closeDriver(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);

        driver.setStatus("closed");
        driverRepository.save(driver);
    }
}
