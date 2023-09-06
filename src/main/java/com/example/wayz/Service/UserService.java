package com.example.wayz.Service;

import com.example.wayz.Model.User;
import com.example.wayz.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;




}
