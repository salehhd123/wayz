package com.example.wayz.Service;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @InjectMocks
    DriverService driverService;

    @Mock
    DriverRepository driverRepository;

    @Mock
    AuthRepository authRepository;

    User user;

    Driver driver1;
    Driver driver2;

    List<Driver> drivers;


    @BeforeEach
    void setUp() {
        user = new User(null,"0501111111","12345","DRIVER",
                null,null,null);

        driver1=new Driver(null,"bakr","pending",null,null,null,null,5,user,null,null,null);
        driver2=new Driver(null,"bakr2","pending",null,null,null,null,5,user,null,null,null);

        drivers=new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
    }

    @Test
    void getAllDrivers() {
        when(driverRepository.findAll()).thenReturn(drivers);

        List<Driver> driverList=driverService.getAllDrivers();
        Assertions.assertEquals(driverList,drivers);

        verify(driverRepository,times(1)).findAll();
    }

    @Test
    void updateDriverName() {
        when(driverRepository.findDriverById(driver1.getId())).thenReturn(driver1);

        driverService.updateDriverName(driver1.getId(),driver2.getName());

        verify(driverRepository,times(1)).findDriverById(driver1.getId());
        verify(driverRepository,times(1)).save(driver1);
    }

    @Test
    void approveDriver() {
        when(driverRepository.findDriverById(driver1.getId())).thenReturn(driver1);

        DriverService driverService1=new DriverService(driverRepository);
        driverService1.approveDriver(driver1.getId());

        verify(driverRepository,times(1)).save(driver1);
        Assertions.assertEquals("approved",driver1.getStatus());
    }

    @Test
    void closeDriver() {
        when(driverRepository.findDriverById(driver1.getId())).thenReturn(driver1);

        DriverService driverService1=new DriverService(driverRepository);
        driverService1.closeDriver(driver1.getId());

        verify(driverRepository,times(1)).save(driver1);
        Assertions.assertEquals("closed",driver1.getStatus());
    }
}