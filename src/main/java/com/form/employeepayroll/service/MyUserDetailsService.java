package com.form.employeepayroll.service;

import com.form.employeepayroll.model.User;
import com.form.employeepayroll.model.UserPrinciple;
import com.form.employeepayroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        user=userRepository.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User Not Found 404");

        return new UserPrinciple(user);
    }
}
