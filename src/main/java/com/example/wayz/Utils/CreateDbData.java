package com.example.wayz.Utils;

import com.example.wayz.Model.*;
import com.example.wayz.Repository.*;
import com.example.wayz.Service.UserTripsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateDbData {

    private final StudentRepository studentRepository;

    private final AuthRepository authRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final DriverTripsRepository driverTripsRepository;
    private final StudentTripsRepository studentTripsRepository;
    private final UserTripsService userTripsService;

//    @Bean

    public void addData() {

        int run = 0; // edit this manually to fill the database

        if(run == 1) {

            int STUDENT_COUNT = 160;
            int DRIVER_COUNT = 32;

            String[] names = new String[] {"saleh", "saud", "bakr", "abdullah"};


            for (int i = 2; i < STUDENT_COUNT; i+=2) {

                User u1 = new User();
                u1.setUsername("050123456" + i);
                u1.setPassword((new BCryptPasswordEncoder()).encode("1"));
                u1.setRole("STUDENT");

                Student s1 = new Student(null, "st1", "noura", "google.com", 100, u1, Set.of(), Set.of(), Set.of());
                studentRepository.save(s1);



                TimeRange[] morningTimeRanges = new TimeRange[3];

                morningTimeRanges[0] = new TimeRange("08:00 - 08:29", LocalDateTime.now().withHour(6).withMinute(0), LocalDateTime.now().withHour(10).withMinute(29));
                morningTimeRanges[1] = new TimeRange("10:30 - 12:29", LocalDateTime.now().withHour(10).withMinute(30), LocalDateTime.now().withHour(12).withMinute(29));
                morningTimeRanges[2] = new TimeRange("12:30 - 14:29", LocalDateTime.now().withHour(12).withMinute(30), LocalDateTime.now().withHour(14).withMinute(29));


                int index = (int) Math.floor(Math.random() * morningTimeRanges.length);

                // student trips
                StudentTrips studentTrips = new StudentTrips(null, 1, "going", morningTimeRanges[index].getStart(), null, null);

                studentTripsRepository.save(studentTrips);
            }

            for (int i = 1; i < DRIVER_COUNT; i+=2) {

                User u2 = new User();
                u2.setUsername("050123456" + i);
                u2.setPassword((new BCryptPasswordEncoder()).encode("1"));
                u2.setRole("DRIVER");

                Driver driver = new Driver();
                driver.setUser(u2);
                driver.setStatus("approved");

                int index = (int) Math.floor((Math.random() * names.length));
                driver.setName(names[index]);

                driverRepository.save(driver);


                Car car = new Car(null, 2019, 10, "abcdefg", "sedan", driver);
                carRepository.save(car);


                TimeRange[] morningTimeRanges = new TimeRange[3];

                morningTimeRanges[0] = new TimeRange("06:00 - 10:29", LocalDateTime.now().withHour(6).withMinute(0), LocalDateTime.now().withHour(10).withMinute(29));
                morningTimeRanges[1] = new TimeRange("10:30 - 12:29", LocalDateTime.now().withHour(10).withMinute(30), LocalDateTime.now().withHour(12).withMinute(29));
                morningTimeRanges[2] = new TimeRange("12:30 - 14:29", LocalDateTime.now().withHour(12).withMinute(30), LocalDateTime.now().withHour(14).withMinute(29));


                for (TimeRange timeRange : morningTimeRanges) {
                    DriverTrips driverTrips = new DriverTrips(null, "going", timeRange.getStart(), timeRange.getEnd(), driver);
                    driverTripsRepository.save(driverTrips);
                }



            }



            // user trips
            userTripsService.createUserTrips();






        }




    }
}
