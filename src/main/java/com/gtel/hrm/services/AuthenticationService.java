package com.gtel.hrm.services;


import com.gtel.hrm.dto.request.AuthenticationRequest;
import com.gtel.hrm.exception.AppException;
import com.gtel.hrm.exception.ErrorCode;
import com.gtel.hrm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo;

    public boolean authenticated(AuthenticationRequest authenticationRequest) {
        var user = userRepo.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }
}
