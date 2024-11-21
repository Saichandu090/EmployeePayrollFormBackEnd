package com.form.employeepayroll.controller;

import com.form.employeepayroll.model.User;
import com.form.employeepayroll.service.JWTService;
import com.form.employeepayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
        {
            return new ResponseEntity<>(jwtService.generateToken(user.getUsername()),HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);
    }
}
