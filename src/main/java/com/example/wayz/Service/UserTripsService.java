package com.example.wayz.Service;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Model.UserTrips;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.StudentTripsRepository;
import com.example.wayz.Repository.UserTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTripsService {

    private final UserTripsRepository userTripsRepository;
    private final StudentTripsRepository studentTripsRepository;
    private final DriverRepository driverRepository;



    public List<UserTrips> findAll() {
        return userTripsRepository.findAll();
    }


    public void createUserTrips() {

        List<StudentTrips> studentTrips = studentTripsRepository.findAll();
        List<Driver> drivers = driverRepository.findAll();


        // O(n^2)

        for (Driver driver : drivers) {
            int counter = driver.getCar().getSeats();

            UserTrips userTrips = new UserTrips();
            userTrips.setDriver(driver);

            for (StudentTrips studentTrips1 : studentTrips) {
                if(counter == 0) {
                    break;
                }

                userTrips.getStudentTrips().add(studentTrips1);

                counter--;
            }

            userTripsRepository.save(userTrips);
        }


    }






}
