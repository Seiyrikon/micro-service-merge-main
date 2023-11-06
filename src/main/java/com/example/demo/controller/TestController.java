package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProjectInvolved;
import com.example.demo.model.UserInfoOutput;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/{emp_id}")
    public UserInfoOutput testApi(@PathVariable String emp_id) {
        return userService.getUserById(emp_id);
    }

    @GetMapping("/tech/{projId}")
    public List<Map<String,Object>> techApi(@PathVariable String projId) {
        return projectService.getAllTechnologiesOfProject(projId);
    }

    @GetMapping("/devPhase/{projId}")
    public List<Map<String,Object>> phaseApi(@PathVariable String projId) {
        return projectService.getAllPhasesOfProject(projId);
    }
}
