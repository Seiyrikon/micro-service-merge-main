package com.example.demo.service.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.UserOutput;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AuthResponse authUser(String username, String password) {
        UserOutput user = userDao.getByUsername(username);
        String hashedPassword = user.getPassword();
        AuthResponse response = new AuthResponse();
        
        if (user != null && bCryptPasswordEncoder.matches(password, hashedPassword)) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setErrorMessage("Incorrect password.");
        }
        return response;
    }

    @Override
    public Map<String, String> logout(String emp_id) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "logout success");

        return response;
    }

}
