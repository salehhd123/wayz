package com.example.wayz.Controller;


import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> registerDriver(@RequestBody @Valid DriverDTO driverDTO){

        authService.registerDriver(driverDTO);
        return ResponseEntity.status(200).body("user registered");
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.status(200).body("logout successfully");
    }

}

