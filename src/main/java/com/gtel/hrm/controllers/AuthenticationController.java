package com.gtel.hrm.controllers;


import com.gtel.hrm.dto.request.AuthenticationRequest;
import com.gtel.hrm.dto.request.ApiResponse;
import com.gtel.hrm.dto.request.IntrospectRequest;
import com.gtel.hrm.dto.response.AuthenticationResponse;
import com.gtel.hrm.dto.response.IntrospectResponse;
import com.gtel.hrm.services.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(result)
                .build();
//        return new ApiResponse<>(apiResponse.getCode(),"Login succesful", result);
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(result)
                .build();
//        return new ApiResponse<>(apiResponse.getCode(),"Login succesful", result);
    }

}
