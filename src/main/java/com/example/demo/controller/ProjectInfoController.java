package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserInfoOutput;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user-project")
public class ProjectInfoController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // String proj_id = "2";
    
    @GetMapping("/{projId}")
    public String index (@PathVariable String projId, Model model) {

        model.addAttribute("content", "fragments/Project :: Project");
        model.addAttribute("projectInfo", projectService.getAttributesOfProject(projId));
        model.addAttribute("clientName", projectService.getClientOfProject(projId));
        model.addAttribute("devPhase", projectService.getAllPhasesOfProject(projId));
        model.addAttribute("devType", projectService.getDevelopmentTypeOfProject(projId));
        model.addAttribute("tech", projectService.getAllTechnologiesOfProject(projId));
        model.addAttribute("status", projectService.getStatusOfProject(projId));
        model.addAttribute("projManager", projectService.getAllManagersOfProject(projId));
        model.addAttribute("members", projectService.getAllMembersOfProject(projId));
        return "index";
    }

    @GetMapping("/UserPage")
    public String UserPage (Model model) {
        model.addAttribute("content", "fragments/UserPage :: UserPage");
        return "index";
    }
}
