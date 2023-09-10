package com.example.wayz.Service;

import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final AuthRepository authRepository;
    private final FileService fileService;

    public void registerStudent(StudentDTO studentDto) {
        User user = new User(null, studentDto.getUsername(), studentDto.getPassword(), "STUDENT", null, null, null);
        Student student = new Student(null, studentDto.getName(), studentDto.getUniversity(), studentDto.getHomeGoogleMapUrl(),
                0, user, null, null, null);

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        studentRepository.save(student);
    }

    public void registerDriver(String data, MultipartFile id, MultipartFile license, MultipartFile registration, MultipartFile pic) throws IOException {

        DriverDTO driverDTO = new ObjectMapper().readValue(data, DriverDTO.class);

        User user = new User(null, driverDTO.getUsername(), driverDTO.getPassword(), "DRIVER", null, null, null);

        authRepository.save(user);

        Driver driver = new Driver();
        driver.setUser(user);
        driver.setStatus("pending");
        driver.setName(driverDTO.getName());
        driver.setUnCashedTrips(0);

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        driver = driverRepository.save(driver); // make sure to get the ID


        // save driver files.
        HashMap<String, MultipartFile> files = new HashMap<>(4) {{
            put("id", id);
            put("license", license);
            put("registration", registration);
            put("pic", pic);
        }};

        fileService.uploadDriverDocuments(files, driver.getId());

//        {"name":"abdullah", "password": "12345678910@nN", "username": "0512263921"}
    }
}
