package com.example.wayz.Service;

import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final AuthRepository authRepository;

    public void registerStudent(StudentDTO studentDto){
        User user = new User(null,studentDto.getUsername(),studentDto.getPassword(),"STUDENT",null,null,null);
        Student student=new Student(null,studentDto.getName(),studentDto.getUniversity(),studentDto.getHomeGoogleMapUrl(),
                0,0.0,user,null,null,null,null);

        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        studentRepository.save(student);
    }

    public void registerDriver(DriverDTO driverDTO){
        User user = new User(null,driverDTO.getUsername(),driverDTO.getPassword(),"driver",null,null,null);
        Driver driver=new Driver(null,driverDTO.getStatus(),driverDTO.getDriverLicenceImgPath(),driverDTO.getCarRegistrationImgPath(),
                driverDTO.getDriverImgPath(), driverDTO.getGovIdImgPath(),user,null,null, null);

        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        driverRepository.save(driver);
    }
}
