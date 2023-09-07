package com.example.wayz.Service;

import com.example.wayz.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository userRepository;

}
