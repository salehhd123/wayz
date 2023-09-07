package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.CarDTO;
import com.example.wayz.Model.Car;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Orders;
import com.example.wayz.Repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

//    public void addCar(CarDTO car) {
//        Car newCar = new Car(null, car.getModel(), car.getSeats(), car.getCarPlate(), car.getType(), null);
//
//        carRepository.save(newCar);
//
//    }

    public void updateCar(Integer driverId, CarDTO carUpdate) {
        Car carInDb = carRepository.findCarByDriverId(driverId);

        if (carInDb == null) {
            throw new ApiException("Could not find this car");
        }
        carInDb.setPlate(carUpdate.getCarPlate());
        carInDb.setType(carUpdate.getType());
        carInDb.setSeats(carUpdate.getSeats());
        carInDb.setModelYear(carUpdate.getModel());

        carRepository.save(carInDb);
    }
}
