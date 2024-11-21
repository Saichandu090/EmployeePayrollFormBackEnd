package com.form.employeepayroll.service;

import com.form.employeepayroll.model.User;
import com.form.employeepayroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User registerUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
