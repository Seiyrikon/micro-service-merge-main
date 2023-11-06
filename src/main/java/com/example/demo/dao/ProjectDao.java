package com.example.demo.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.ClientOutput;
import com.example.demo.model.DevPhaseOutput;
import com.example.demo.model.DevTypeOutput;
import com.example.demo.model.ProjectInvolved;
import com.example.demo.model.ProjectOutput;
import com.example.demo.model.ProjectStatusOutput;
import com.example.demo.model.TechnologyOutput;
import com.example.demo.model.UserInfoOutput;


@Mapper
public interface ProjectDao {
  // public List<ProjectTable> projectTable();

    //get all managers of a certain project
    List<UserInfoOutput> getAllManagersOfProject(String proj_id);
    //get all development phases of a project
    List<DevPhaseOutput> getAllPhasesOfProject(String proj_id);
    //get all members of a project
    List<UserInfoOutput> getAllMembersOfProject(String proj_id);
    List<UserInfoOutput> getAllMembersOfProjectForTable(String proj_id);
    //get all development technologies of a project
    List<TechnologyOutput> getAllTechnologiesOfProject(String proj_id);
    //get the client of a project
    ClientOutput getClientOfProject(String proj_id);
    //get the development type of a project
    List<DevTypeOutput> getDevelopmentTypeOfProject(String proj_id);
    //get the status of a project
    ProjectStatusOutput getStatusOfProject(String proj_id);

    List<ProjectOutput> getAllProjects(String proj_id);

    List<ProjectOutput> getAllProject();

    ProjectOutput getProjectById(String id);
    
    List<ProjectInvolved> getAllProjectsOfUser(String emp_id);
  
}
