package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.AuthResponse;

public interface AuthService {

    public AuthResponse authUser(String username, String password);
    
    public Map<String, String> logout(String emp_id);
}
