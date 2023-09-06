package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiException("Wrong username or password");
        }
        return user;    }
}
