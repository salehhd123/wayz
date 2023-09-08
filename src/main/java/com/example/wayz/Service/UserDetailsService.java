package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepository authRepository;
    private final ReportRepository reportRepository;
    private final DriverService driverService;
    private final DriverRepository driverRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, ApiException {
        User user = authRepository.findUserByUsername(username);


        if (user == null) {
            throw new ApiException("Wrong username or password.");
        }

        if (driverRepository.findDriverById(user.getId()).getStatus().equalsIgnoreCase("pending")) {
            throw new ApiException("You cannot login your account is still pending.");
        } else if (driverRepository.findDriverById(user.getId()).getStatus().equalsIgnoreCase("closed")) {
            throw new ApiException("YOU ARE FIRED, GET OUT.");
        }

        if (reportRepository.countApproved(user.getId()) > 2) {
            driverService.closeDriver(user.getId());
            throw new ApiException("YOU ARE FIRED, GET OUT.");
        }

        return user;
    }
}
