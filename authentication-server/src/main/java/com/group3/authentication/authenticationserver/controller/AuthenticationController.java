package com.group3.authentication.authenticationserver.controller;

import com.group3.authentication.authenticationserver.exceptions.EntityNotFoundException;
import com.group3.authentication.authenticationserver.model.StudentAccount;
import com.group3.authentication.authenticationserver.request.AuthenticationRequest;
import com.group3.authentication.authenticationserver.response.JWTTokenResponse;
import com.group3.authentication.authenticationserver.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTTokenResponse> createStudentAccount(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.generateJWTToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudentAccount(@RequestBody StudentAccount studentAccount){
        authenticationService.registerStudentAccount(studentAccount);
        return new ResponseEntity<>("StudentAccountCreated", HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
