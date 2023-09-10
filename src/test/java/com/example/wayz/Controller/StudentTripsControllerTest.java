package com.example.wayz.Controller;

import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.StudentRepository;
import com.example.wayz.Service.StudentService;
import com.example.wayz.Service.StudentTripsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentTripsController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})

class StudentTripsControllerTest {

    @MockBean
    StudentTripsService studentTripsService;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    MockMvc mockMvc;

    StudentTrips studentTrips1,studentTrips2;

    User user;
    Student student;

    ApiResponse apiResponse;


    List<StudentTrips> studentTripsList1,studentTripsList2;

    @BeforeEach
    void setUp() {
        user = new User(null,"0501111111","12345Abcdefghi_#12345","STUDENT",
                null,null,null);

        student=new Student(null,"sara","PNU","Location",20,user,null,null,null);
        studentRepository.save(student);

        studentTrips1=new StudentTrips(null,5,"going",LocalDateTime.of(2023, 9, 8, 15, 30, 45),student,null);
        studentTrips2=new StudentTrips(null,5,"return", LocalDateTime.now().plusDays(1),student,null);

        studentTripsList1= Arrays.asList(studentTrips1);
        studentTripsList2=Arrays.asList(studentTrips2);
    }

    @Test
    void getAllStudentTrips() throws Exception {
        Mockito.when(studentTripsService.getAllStudentTrips()).thenReturn(studentTripsList1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student-trips/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("going"));

    }

//    @Test
//    void addStudentTrip() throws  Exception {
//        mockMvc.perform(post("/api/v1/student-trips/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content( new ObjectMapper().writeValueAsString(studentTrips1)))
//                .andExpect(status().isOk());
//    }

    @Test
    void getStudentTripsByTypeIgnoreCase() throws Exception {
        String type = "going"; // the type we want to test
        Mockito.when(studentTripsService.getTripsByType(type)).thenReturn(studentTripsList1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student-trips/findByTypeIgnoreCase/{type}", type)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("going"));
    }
}