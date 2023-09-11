package com.example.wayz.Controller;


import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.User;
import com.example.wayz.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(@RequestBody @Valid StudentDTO studentDto){

        authService.registerStudent(studentDto);
        return ResponseEntity.status(200).body("user registered");
    }

    @PostMapping("/register/driver")
    public ResponseEntity<String> registerDriver(
                    @RequestParam("data") String data,
                    @RequestParam("car") String car,
                    @RequestParam("id") MultipartFile id,
                    @RequestParam("license") MultipartFile license,
                    @RequestParam("registration") MultipartFile registration,
                    @RequestParam("pic") MultipartFile pic
            ) throws IOException
    {
        authService.registerDriver(data, car, id, license, registration, pic);
        return ResponseEntity.status(200).body("user registered");
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.status(200).body("logout successfully");
    }

}

