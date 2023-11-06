package  com.example.demo.service.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjInfoDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.ClientOutput;
import com.example.demo.model.DevPhaseOutput;
import com.example.demo.model.DevTypeOutput;
import com.example.demo.model.ProjInfoOutput;
import com.example.demo.model.ProjectInvolved;
import com.example.demo.model.ProjectOutput;
import com.example.demo.model.ProjectStatusOutput;
import com.example.demo.model.TechnologyOutput;
import com.example.demo.model.UserInfoOutput;
import com.example.demo.service.ProjectService;

// import com.controlcenter.controlcenter.dao.ActivityLogDao;
// import microservice.microservice.dao.ClientDao;
// import microservice.microservice.dao.DevPhaseDao;
// import microservice.microservice.dao.DevTypeDao;
// import microservice.microservice.dao.ProjInfoDao;
// import microservice.microservice.dao.ProjectDao;
// import microservice.microservice.dao.ProjectManagerDao;
// import microservice.microservice.dao.ProjectPhaseDao;
// import microservice.microservice.dao.ProjectTechnologyDao;
// import microservice.microservice.dao.TechnologyDao;
// import microservice.microservice.dao.UserDao;
// import microservice.microservice.dao.UserProjectDao;
// // import com.controlcenter.controlcenter.model.ActivityLogInput;
// import microservice.microservice.model.ClientOutput;
// import microservice.microservice.model.DevPhaseOutput;
// import microservice.microservice.model.DevTypeOutput;
// import microservice.microservice.model.ProjInfoOutput;
// import microservice.microservice.model.ProjectOutput;
// import microservice.microservice.model.ProjectPhaseOutput;
// import microservice.microservice.model.ProjectStatusOutput;
// // import microservice.microservice.model.ProjectTable;
// import microservice.microservice.model.ProjectTechnologyOutput;
// import microservice.microservice.model.TechnologyOutput;
// import microservice.microservice.model.UserInfoOutput;
// // import microservice.microservice.model.UserOutput;
// import microservice.microservice.model.UserProjectOutput;
// import microservice.microservice.service.ProjectService;
// import com.controlcenter.controlcenter.shared.TimeFormatter;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProjInfoDao projInfoDao;

    public ProjectOutput getProjectById(String id){
        return projectDao.getProjectById(id);
    };
    // @Autowired
    // public ProjectService projectService;

    // @Autowired
    // public ActivityLogDao activityLogDao;

    // @Autowired
    // public TimeFormatter timeFormatter;

    // @Override
    // public ResponseEntity<List<ProjectTable>> projectTable() {
    //     return ResponseEntity.ok(projectDao.projectTable());
    // }

    @Override
    public List<ProjectInvolved> getAllProjectsOfUser(String emp_id) {

        return projectDao.getAllProjectsOfUser(emp_id);
    }

    // get all managers of a project
    @Override
    public List<Map<String, Object>> getAllManagersOfProject(String proj_id) {
        List<UserInfoOutput> managersOfProject = projectDao.getAllManagersOfProject(proj_id);
        List<Map<String, Object>> allManagers = managersOfProject.stream()
                .map(manager -> {
                    Map<String, Object> currentManagers = new HashMap<>();
                    String fullName = manager.getFname() + " " + manager.getLname();
                    currentManagers.put("manager_name", fullName);
                    return currentManagers;
                }).collect(Collectors.toList());

        return allManagers;
    }

    // get all development phases of a project
    @Override
    public List<Map<String, Object>> getAllPhasesOfProject(String proj_id) {
        List<DevPhaseOutput> phasesOfProject = projectDao.getAllPhasesOfProject(proj_id);
        List<Map<String, Object>> allPhases = phasesOfProject.stream()
                .map(phase -> {
                    Map<String, Object> currentPhases = new HashMap<>();
                    currentPhases.put("dev_phase_name", phase.getDev_phase_name());
                    return currentPhases;
                }).collect(Collectors.toList());

        return allPhases;
    }

    // get all members of a project
    @Override
    public List<Map<String, Object>> getAllMembersOfProject(String proj_id) {
        List<UserInfoOutput> membersOfProject = projectDao.getAllMembersOfProject(proj_id);
    
        List<Map<String, Object>> allMembers = membersOfProject.stream()
                .map(member -> {
                    Map<String, Object> currentMembers = new HashMap<>();
                    String fullName = member.getFname() + " " + member.getLname();
                    currentMembers.put("member_name", fullName);
    
                    // Extract the first letter of the full name
                    char firstLetter = fullName.charAt(0);
                    currentMembers.put("first_letter", String.valueOf(firstLetter));
    
                    return currentMembers;
                }).collect(Collectors.toList());
    
        return allMembers;
    }
    

    // get all development technologies of a project
    @Override
    public List<Map<String, Object>> getAllTechnologiesOfProject(String proj_id) {
        List<TechnologyOutput> technologiesOfProject = projectDao.getAllTechnologiesOfProject(proj_id);
        List<Map<String, Object>> allTechnologies = technologiesOfProject.stream()
                .map(technology -> {
                    Map<String, Object> currentTechnologies = new HashMap<>();
                    currentTechnologies.put("tech_name", technology.getTech_name());
                    return currentTechnologies;
                }).collect(Collectors.toList());

        return allTechnologies;
    }

    // get the client of a project
    @Override
    public Map<String, Object> getClientOfProject(String proj_id) {
        ClientOutput clientOfProject = projectDao.getClientOfProject(proj_id);
        Map<String, Object> currentClient = new HashMap<>();
        currentClient.put("client_name", clientOfProject.getClient_name());
        return currentClient;
    }

    // get the development type of a project
    @Override
    public List<Map<String, Object>> getDevelopmentTypeOfProject(String proj_id) {
        List<DevTypeOutput> developmentTypeOfProject = projectDao.getDevelopmentTypeOfProject(proj_id);
        List<Map<String, Object>> allDevelopmentType = developmentTypeOfProject.stream()
                .map(developmentType -> {
                    Map<String, Object> currentDevelopmentType = new HashMap<>();
                    currentDevelopmentType.put("dev_type_id", developmentType.getDev_type_name());
                    return currentDevelopmentType;
                }).collect(Collectors.toList());

        return allDevelopmentType;
    }

    // get the status of a project
    @Override
    public Map<String, Object> getStatusOfProject(String proj_id) {
           ProjectStatusOutput statusOfProject = projectDao.getStatusOfProject(proj_id);
           Map<String, Object> currentStatus = new HashMap<>();
           currentStatus.put("status_code", statusOfProject.getProj_status_name());
           return currentStatus;
    }

    // @Override
    // public ResponseEntity<List<ProjectOutput>> getAllProject() {
    //     return ResponseEntity.ok(projectDao.getAllProject());
    // }

    // get the attributes of a project
    @Override
    public Map<String, Object> getAttributesOfProject(String proj_id) {
        ProjectOutput project = projectDao.getProjectById(proj_id);

        ProjInfoOutput projInfo = projInfoDao.getProjInfoById(proj_id);

        List<UserInfoOutput> projectManagers = projectDao.getAllManagersOfProject(proj_id);

        List<String> allManagers = projectManagers
                .stream()
                .map(manager -> {
                    return manager.getEmp_id();
                }).collect(Collectors.toList());

        List<DevTypeOutput> developmentTypes = projectDao.getDevelopmentTypeOfProject(proj_id);

        List<Long> allDevelopmentTypes = developmentTypes
                .stream()
                .map(developmentType -> {
                    return developmentType.getDev_type_id();
                }).collect(Collectors.toList());

        List<DevPhaseOutput> developmentPhases = projectDao.getAllPhasesOfProject(proj_id);

        List<Long> allDevelopmentPhases = developmentPhases
                .stream()
                .map(developmentPhase -> {
                    return developmentPhase.getDev_phase_id();
                }).collect(Collectors.toList());

        List<TechnologyOutput> technologies = projectDao.getAllTechnologiesOfProject(proj_id);

        List<Long> allTechnologies = technologies
                .stream()
                .map(technology -> {
                    return technology.getTech_id();
                }).collect(Collectors.toList());

        List<UserInfoOutput> members = projectDao.getAllMembersOfProjectForTable(proj_id);

        List<String> allMembers = members
                .stream()
                .map(member -> {
                    return member.getEmp_id();
                }).collect(Collectors.toList());

        Map<String, Object> projectAttributes = new HashMap<>();
        projectAttributes.put("proj_name", project.getProj_name());
        projectAttributes.put("proj_code", project.getProj_code());
        projectAttributes.put("proj_desc", project.getProj_description());
        projectAttributes.put("manager_emp_id", allManagers);
        projectAttributes.put("client_id", projInfo.getClient_id());
        projectAttributes.put("start_date", project.getStart_date());
        projectAttributes.put("end_date", project.getEnd_date());
        projectAttributes.put("dev_type_id", allDevelopmentTypes);
        projectAttributes.put("dev_phase_id", allDevelopmentPhases);
        projectAttributes.put("tech_id", allTechnologies);
        projectAttributes.put("status_code", projInfo.getProj_status_id());
        projectAttributes.put("member_emp_id", allMembers);

        return projectAttributes;
    }

    // @Override
    // public ProjectOutput getProjectById(String id) {
    //     return projectDao.getProjectById(id);
    // }

//     @Override
//     public ResponseEntity<String> addProject(ProjectOutput project, String emp_id, List<String> manager_ids,
//             Long client_id, Long type_id, List<Long> phase_ids, List<Long> tech_ids, Long project_status_id,
//             List<String> member_ids) {
//         ProjInfoInput projectInfo = new ProjInfoInput();
//         ProjectManagerInput projectManager = new ProjectManagerInput();
//         ProjectPhaseInput projectPhase = new ProjectPhaseInput();
//         ProjectTechnologyInput projectTechnology = new ProjectTechnologyInput();
//         UserProjectInput userProject = new UserProjectInput();

//         try {

//             if(manager_ids == null) {
//                 return ResponseEntity.status(400).body("Select a manager");
//             }

//             Date start_date = project.getStart_date();
//             Date end_date = project.getEnd_date();

//             Integer compareTo = start_date.compareTo(end_date);

//             if(compareTo > 0) {
//                 return ResponseEntity.status(400).body("Invalid start date and end date.");
//             }
            
//             projectDao.addProject(project);

//             Long projectToBeSaved = project.getProj_id();

//             // initializing the value of project info before saving.
//             if(type_id == null) {
//                 projectInfo.setDev_type_id(1L);
//                 projectInfo.setClient_id(client_id);
//                 projectInfo.setProj_status_id(project_status_id);
//                 projectInfo.setProj_id(project.getProj_id());
//             } else {
//                 projectInfo.setDev_type_id(type_id);
//                 projectInfo.setClient_id(client_id);
//                 projectInfo.setProj_status_id(project_status_id);
//                 projectInfo.setProj_id(project.getProj_id());
//             }
            
//             // saving of project info on tbl_proj_info table
//             projInfoDao.addProjInfo(projectInfo);

//             // iterating all manager_ids which are emp_id and saving them to be multiple
//             // project manager
//             for (String manager_id : manager_ids) {
//                 projectManager.setProj_id(projectToBeSaved);
//                 projectManager.setEmp_id(manager_id);
//                 projectManagerDao.addManagers(projectManager);
//             }

//             // iterating all development phase id before saving them to be multiple project
//             // development phase
//             for (Long phase_id : phase_ids) {
//                 projectPhase.setDev_phase_id(phase_id);
//                 projectPhase.setProj_id(projectToBeSaved);
//                 projectPhaseDao.addProjectPhase(projectPhase);
//             }

//             // iterating all technology id before saving them to be multiple project
//             // technology
//             for (Long tech_id : tech_ids) {
//                 projectTechnology.setTech_id(tech_id);
//                 projectTechnology.setProj_id(projectToBeSaved);
//                 projectTechnologyDao.addProjectTechnology(projectTechnology);
//             }

//             // iterating all employee id before saving them as project members
//             for (String member_id : member_ids) {
//                 userProject.setEmp_id(member_id);
//                 userProject.setProj_id(projectToBeSaved);
//                 userProjectDao.addUserProject(userProject);
//             }

//             // Activitylog
//             ActivityLogInput activityLogInput = new ActivityLogInput();

//             activityLogInput.setEmp_id(emp_id); // current logged user dapat
//             activityLogInput.setLog_desc("Added '" + project.getProj_name() + "' project.");

//             Long currentTimeMillis = System.currentTimeMillis();
//             // add the activity log
//             activityLogInput.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//             activityLogDao.addActivityLog(activityLogInput);

//             return ResponseEntity.status(200).body("Project '" + project.getProj_name() + "' added successfully.");
//         } catch (Exception e) {
//             return ResponseEntity.status(400).body(e.getMessage());
//         }
//     }

//     @Override
//     public ResponseEntity<String> editProjectInfo(String id, ProjectInput projectBody, String emp_id,
//             List<String> manager_ids, Long client_id, Long type_id, List<Long> phase_ids, List<Long> tech_ids,
//             Long project_status_id, List<String> member_ids) {


//         ProjectOutput project = projectDao.getProjectById(id);
//         ProjInfoOutput projectInfoFromDB = projInfoDao.getProjInfoById(id);

//         ProjInfoInput projectInfo = new ProjInfoInput();

//         ProjectManagerInput projectManager = new ProjectManagerInput();
//         ProjectPhaseInput projectPhase = new ProjectPhaseInput();
//         ProjectTechnologyInput projectTechnology = new ProjectTechnologyInput();
//         UserProjectInput projectMember = new UserProjectInput();

//         List<UserOutput> listOfManagers = userDao.getAllUser();
//         List<ProjectPhaseOutput> listOfDevelopmentPhases = projectPhaseDao.getAllProjectPhase();
//         List<ProjectTechnologyOutput> listOfDevelopmentTechnologies = projectTechnologyDao.getAllProjectTechnology();
//         List<UserOutput> listOfMembers = userDao.getAllUser();

//         if (project != null) {
//             if (project.getDel_flag() == 1) {
//                 return ResponseEntity.status(400).body("Project with the ID " + id + " has already been deleted.");
//             } else {

//                 //gets all the id of the managers, development phase, development technology, and members to compare to the request params.
//                 List<String> manager_ids_list = projectDao.getAllManagersOfProject(id)
//                 .stream()
//                 .map(manager -> {
//                     return manager.getEmp_id();
//                 }).collect(Collectors.toList());
// //anakin skywalker
//                 List<Long> phase_id_list = projectDao.getAllPhasesOfProject(id)
//                 .stream()
//                 .map(phase -> {
//                     return phase.getDev_phase_id();
//                 }).collect(Collectors.toList());

//                 List<Long> tech_id_list = projectDao.getAllTechnologiesOfProject(id)
//                 .stream()
//                 .map(technology -> {
//                     return technology.getTech_id();
//                 }).collect(Collectors.toList());

//                 List<String> member_ids_list = projectDao.getAllMembersOfProject(id)
//                 .stream()
//                 .map(member -> {
//                     return member.getEmp_id();
//                 }).collect(Collectors.toList());

//                 if(type_id == null) {
//                     type_id = 1L;
//                 }
                
//                 //gets all the id of the managers, development phase, development technology, and members to compare to the request params.

//                 //check if value of information on project have been changed.
//                 if(project.getProj_name().equals(projectBody.getProj_name())
//                    && project.getProj_description().equals(projectBody.getProj_description())
//                    && project.getStart_date().equals(projectBody.getStart_date())
//                    && project.getEnd_date().equals(projectBody.getEnd_date())
//                    && projectInfoFromDB.getClient_id() == client_id
//                    && projectInfoFromDB.getDev_type_id() == type_id
//                    && projectInfoFromDB.getProj_status_id() == project_status_id
//                    && manager_ids_list.equals(manager_ids)
//                    && phase_id_list.equals(phase_ids)
//                    && tech_id_list.equals(tech_ids)
//                    && (member_ids_list.equals(member_ids) || (member_ids_list.size() == 0 && member_ids == null))
//                 ) {
//                     return ResponseEntity.ok().body("No changes were made");
//                 }
//                 // initializing the value of project
//                 Map<String, Object> projectMap = new HashMap<>();

//                 // putting the values into hashmap
//                 projectMap.put("id", id);
//                 projectMap.put("project", projectBody);

//                 // initializing the value of project info before saving.
//                 Map<String, Object> projectInfoMap = new HashMap<>();
//                 projectInfo.setDev_type_id(type_id);
//                 projectInfo.setClient_id(client_id);
//                 projectInfo.setProj_status_id(project_status_id);
//                 projectInfo.setProj_id(project.getProj_id());

//                 // putting the values into hashmap
//                 projectInfoMap.put("id", id);
//                 projectInfoMap.put("projInfo", projectInfo);

//                 if(manager_ids == null) {
//                     return ResponseEntity.status(400).body("Select a manager");
//                 }

//                 Date start_date = projectBody.getStart_date();
//                 Date end_date = projectBody.getEnd_date();

//                 Integer compareTo = start_date.compareTo(end_date);

//                 if(compareTo > 0) {
//                     return ResponseEntity.status(400).body("Invalid start date and end date.");
//                 }

//                 // deleting all records from composite table tbl_proj_manager
//                 for (UserOutput manager : listOfManagers) {
//                     projectManager.setProj_id(project.getProj_id());
//                     projectManager.setEmp_id(manager.getEmp_id());
//                     projectManagerDao.permaDeleteProjectManager(projectManager);
//                 }

//                 // adding records from composite table tbl_proj_manager
//                 for (String manager_id : manager_ids) {
//                     projectManager.setProj_id(project.getProj_id());
//                     projectManager.setEmp_id(manager_id);
//                     projectManagerDao.addManagers(projectManager);
//                 }

//                 // deleting all records from composite table tbl_proj_phase
//                 for (ProjectPhaseOutput developmentPhase : listOfDevelopmentPhases) {
//                     projectPhase.setProj_id(project.getProj_id());
//                     projectPhase.setDev_phase_id(developmentPhase.getDev_phase_id());
//                     projectPhaseDao.permaDeleteProjectPhase(projectPhase);
//                 }

//                 // adding records from composite table tbl_proj_phase
//                 for (Long phase_id : phase_ids) {
//                     projectPhase.setProj_id(project.getProj_id());
//                     projectPhase.setDev_phase_id(phase_id);
//                     projectPhaseDao.addProjectPhase(projectPhase);
//                 }

//                 // deleting all records from composite table tbl_proj_tech
//                 for (ProjectTechnologyOutput developmentTechnology : listOfDevelopmentTechnologies) {
//                     projectTechnology.setProj_id(project.getProj_id());
//                     projectTechnology.setTech_id(developmentTechnology.getTech_id());
//                     projectTechnologyDao.permaDeleteProjectTechnology(projectTechnology);
//                 }

//                 // adding records from composite table tbl_proj_tech
//                 for (Long tech_id : tech_ids) {
//                     projectTechnology.setProj_id(project.getProj_id());
//                     projectTechnology.setTech_id(tech_id);
//                     projectTechnologyDao.addProjectTechnology(projectTechnology);
//                 }

//                 // deleting all records from composite table tbl_proj_members
//                 for (UserOutput member : listOfMembers) {
//                     projectMember.setProj_id(project.getProj_id());
//                     projectMember.setEmp_id(member.getEmp_id());
//                     userProjectDao.permaDeleteProjectMember(projectMember);
//                 }

//                 if(member_ids == null) {
//                     // adding records from composite table tbl_proj_members
//                     projectMember.setProj_id(project.getProj_id());
//                     projectMember.setEmp_id("100");
//                     userProjectDao.addUserProject(projectMember);
//                 } else {
//                     // adding records from composite table tbl_proj_members
//                     for (String member_id : member_ids) {
//                         projectMember.setProj_id(project.getProj_id());
//                         projectMember.setEmp_id(member_id);
//                         userProjectDao.addUserProject(projectMember);
//                     }
//                 }

                
//                 // saving the new value of the project
//                 projectDao.editProjectInfo(projectMap);

//                 // saving of project info on tbl_proj_info table
//                 projInfoDao.editProjInfo(projectInfoMap);

//                 Long currentTimeMillis = System.currentTimeMillis();
//                 if (!project.getProj_name().equals(projectBody.getProj_name())) {
//                     // Activitylog
//                     ActivityLogInput activityLogInput = new ActivityLogInput();

//                     activityLogInput.setEmp_id(emp_id); // current logged user dapat
//                     activityLogInput.setLog_desc("Edited '" + project.getProj_name() + "' to '" + projectBody.getProj_name() + "' project.");

//                     // add the activity log
//                     activityLogInput.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//                     activityLogDao.addActivityLog(activityLogInput);
//                 } else {
//                     // Activitylog
//                     ActivityLogInput activityLogInput = new ActivityLogInput();
    
//                     activityLogInput.setEmp_id(emp_id); // current logged user dapat
//                     activityLogInput.setLog_desc("Edited '" + projectBody.getProj_name() + "' project.");
    
//                     // add the activity log
//                     activityLogInput.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//                     activityLogDao.addActivityLog(activityLogInput);
//                 }


//                 return ResponseEntity.status(200).body("Project '" + projectBody.getProj_name() + "' edited successfully.");
                
//             }
//         } else {
//             return ResponseEntity.status(404).body("Project with the ID " + id + " cannot be found.");
//         }
//     }

//     @Override
//     public String logicalDeleteProject(String id, String emp_id) {
//         ProjectOutput project = projectDao.getProjectById(id);

//         if (project != null) {
//             if (project.getDel_flag() == 1) {
//                 return "Project with the ID " + id + " has already been deleted.";
//             } else {
//                 // setting the del_flag of tbl_project_mst to 1 based on the selected project
//                 projectDao.logicalDeleteProject(id);
//                 // setting the del_flag of tbl_proj_info to 1 based on the selected project
//                 projInfoDao.logicalDeleteProjInfo(id);
//                 // setting the del_flag of tbl_proj_manager to 1 based on the selected project
//                 projectManagerDao.logicalDeleteProjectManager(id);
//                 // setting the del_flag of tbl_proj_phase to 1 based on the selected project
//                 projectPhaseDao.logicalDeleteProjectPhase(id);
//                 // setting the del_flag of tbl_proj_tech to 1 based on the selected project
//                 projectTechnologyDao.logicalDeleteProjectTechnology(id);
//                 // setting the del_flag of tbl_user_project to 1 based on the selected project
//                 userProjectDao.logicalDeleteUserProject(id);

//                 // Activitylog
//                 ActivityLogInput activityLogInput = new ActivityLogInput();

//                 activityLogInput.setEmp_id(emp_id); // current logged user dapat
//                 activityLogInput.setLog_desc("Deleted '" + project.getProj_name() + "' project.");

//                 Long currentTimeMillis = System.currentTimeMillis();
//                 // add the activity log
//                 activityLogInput.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//                 activityLogDao.addActivityLog(activityLogInput);

//                 return "Project '" + project.getProj_name() + "' deleted successfully.";
//             }
//         } else {
//             return "Project with the ID " + id + " cannot be found.";
//         }
//     }

//     @Override
//     public String deleteMultipleProject(List<Long> ids, String emp_id) {
//         Long currentTimeMillis = System.currentTimeMillis();
//         ProjectOutput project = new ProjectOutput();
//         List<String> deletedProjects = new ArrayList<>();
//         StringBuilder projectList = new StringBuilder();

//         for (Long id : ids) {
//             project = projectDao.getProjectById(String.valueOf(id));
//             deletedProjects.add(project.getProj_name());
//         }

//         // Acivitylog for Project
//         ActivityLogInput activityLogProject = new ActivityLogInput();

//         for (String element : deletedProjects) {
//             projectList.append("'").append(element).append("', ");
//         }

//         if (projectList.length() > 0) {
//             projectList.delete(projectList.length() - 2, projectList.length());
//         }

//         if (deletedProjects.size() > 1) {
//             activityLogProject.setEmp_id(emp_id); // current logged user dapat
//             activityLogProject.setLog_desc("Deleted multiple projects: " + projectList.toString() + ".");

//             // add the activity log
//             activityLogProject.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//             activityLogDao.addActivityLog(activityLogProject);
//         } else {
//             activityLogProject.setEmp_id(emp_id); // current logged user dapat
//             activityLogProject.setLog_desc("Deleted " + projectList.toString() + " project.");

//             // add the activity log
//             activityLogProject.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//             activityLogDao.addActivityLog(activityLogProject);
//         }

//         // setting the del_flag of tbl_project_mst to 1 based on the selected project
//         projectDao.deleteMultipleProject(ids);
//         // setting the del_flag of tbl_proj_info to 1 based on the selected project
//         projInfoDao.deleteMultipleProjInfo(ids);
//         // setting the del_flag of tbl_proj_manager to 1 based on the selected project
//         projectManagerDao.deleteMultipleProjectManager(ids);
//         // setting the del_flag of tbl_proj_phase to 1 based on the selected project
//         projectPhaseDao.deleteMultipleProjectPhase(ids);
//         // setting the del_flag of tbl_proj_tech to 1 based on the selected project
//         projectTechnologyDao.deleteMultipleProjectTechnology(ids);
//         // setting the del_flag of tbl_user_project to 1 based on the selected project
//         userProjectDao.deleteMultipleUserProject(ids);


//         return "Records are successfully deleted.";

//         // for (Long id : ids) {
//         // String toString = String.valueOf(id);
//         // ProjectOutput project = projectDao.getProjectById(toString);

//         // //list of project_ids
//         // List<Long> project_ids = projectDao.getAllProjects(toString)
//         // .stream()
//         // .map(proj -> {
//         // return proj.getProj_id();
//         // }).collect(Collectors.toList());

//         // //list of manager_ids(emp_id)
//         // List<String> manager_ids = projectDao.getAllManagersOfProject(toString)
//         // .stream()
//         // .map(manager -> {
//         // return manager.getEmp_id();
//         // }).collect(Collectors.toList());

//         // //list of phase_ids
//         // List<Long> phase_ids = projectDao.getAllPhasesOfProject(toString)
//         // .stream()
//         // .map(phase -> {
//         // return phase.getDev_phase_id();
//         // }).collect(Collectors.toList());

//         // //list of tech_ids
//         // List<Long> tech_ids = projectDao.getAllTechnologiesOfProject(toString)
//         // .stream()
//         // .map(tech -> {
//         // return tech.getTech_id();
//         // }).collect(Collectors.toList());

//         // //list of project members (emp_id)
//         // List<String> member_ids = projectDao.getAllMembersOfProject(toString)
//         // .stream()
//         // .map(member -> {
//         // return member.getEmp_id();
//         // }).collect(Collectors.toList());

//         // if (project != null) {
//         // if (project.getDel_flag() == 1) {
//         // return "Project with the ID " + id + " has already been deleted.";
//         // } else {
//         // // setting the del_flag of tbl_project_mst to 1 based on the selected project
//         // projectDao.deleteMultipleProject(ids);
//         // // setting the del_flag of tbl_proj_info to 1 based on the selected project
//         // projInfoDao.deleteMultipleProjInfo(ids);
//         // // setting the del_flag of tbl_proj_manager to 1 based on the selected
//         // project
//         // projectManagerDao.deleteMultipleProjectManager(ids);
//         // // setting the del_flag of tbl_proj_phase to 1 based on the selected project
//         // projectPhaseDao.deleteMultipleProjectPhase(ids);
//         // // setting the del_flag of tbl_proj_tech to 1 based on the selected project
//         // projectTechnologyDao.deleteMultipleProjectTechnology(ids);
//         // // setting the del_flag of tbl_user_project to 1 based on the selected
//         // project
//         // userProjectDao.deleteMultipleUserProject(ids);

//         // // Acivitylog for Project
//         // ActivityLogInput activityLogProject = new ActivityLogInput();

//         // List<String> projects = new ArrayList<>();
//         // StringBuilder projectList = new StringBuilder();

//         // for (Long project_id : project_ids) {
//         // ProjectOutput proj = projectDao.getProjectById(String.valueOf(project_id));
//         // projects.add(proj.getProj_name());
//         // }

//         // for (String element : projects) {
//         // projectList.append("'").append(element).append("', ");
//         // }

//         // if (projectList.length() > 0) {
//         // projectList.delete(projectList.length() - 2, projectList.length());
//         // }

//         // activityLogProject.setEmp_id(emp_id); // current logged user dapat
//         // activityLogProject.setLog_desc("Deleted multiple projects: " +
//         // projectList.toString() + ".");

//         // // add the activity log
//         // activityLogProject.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//         // activityLogDao.addActivityLog(activityLogProject);

//         // // Acivitylog for Project Manager
//         // ActivityLogInput activityLogManager = new ActivityLogInput();

//         // List<String> managers = new ArrayList<>();
//         // StringBuilder managerList = new StringBuilder();

//         // for (String manager_id : manager_ids) {
//         // UserInfoOutput manager = userDao.getUserById(manager_id);
//         // managers.add(manager.getFname() + " " + manager.getLname());
//         // }

//         // for (String element : managers) {
//         // managerList.append("'").append(element).append("', ");
//         // }

//         // if (managerList.length() > 0) {
//         // managerList.delete(managerList.length() - 2, managerList.length());
//         // }

//         // activityLogManager.setEmp_id(emp_id); // current logged user dapat
//         // activityLogManager.setLog_desc("Deleted multiple managers: " +
//         // managerList.toString() + " from project " + project.getProj_name() + ".");

//         // // add the activity log
//         // activityLogManager.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//         // activityLogDao.addActivityLog(activityLogManager);

//         // // Acivitylog for Project Phase
//         // ActivityLogInput activityLogProjectPhase = new ActivityLogInput();

//         // List<String> phases = new ArrayList<>();
//         // StringBuilder phaseList = new StringBuilder();

//         // for (Long phase_id : phase_ids) {
//         // DevPhaseOutput phase = devPhaseDao.getDevPhaseById(String.valueOf(phase_id));
//         // phases.add(phase.getDev_phase_name());
//         // }

//         // for (String element : phases) {
//         // phaseList.append("'").append(element).append("', ");
//         // }

//         // if (phaseList.length() > 0) {
//         // phaseList.delete(phaseList.length() - 2, phaseList.length());
//         // }

//         // activityLogProjectPhase.setEmp_id(emp_id); // current logged user dapat
//         // activityLogProjectPhase.setLog_desc("Deleted multiple phases: " +
//         // phaseList.toString() + " from project " + project.getProj_name() + ".");

//         // // add the activity log
//         // activityLogProjectPhase.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//         // activityLogDao.addActivityLog(activityLogProjectPhase);

//         // // Acivitylog for Project Technology
//         // ActivityLogInput activityLogProjectTechnology = new ActivityLogInput();

//         // List<String> technologies = new ArrayList<>();
//         // StringBuilder techList = new StringBuilder();

//         // for (Long tech_id : tech_ids) {
//         // TechnologyOutput tech =
//         // technologyDao.getTechnologyById(String.valueOf(tech_id));
//         // technologies.add(tech.getTech_name());
//         // }

//         // for (String element : technologies) {
//         // techList.append("'").append(element).append("', ");
//         // }

//         // if (techList.length() > 0) {
//         // techList.delete(techList.length() - 2, techList.length());
//         // }

//         // activityLogProjectTechnology.setEmp_id(emp_id); // current logged user dapat
//         // activityLogProjectTechnology.setLog_desc("Deleted multiple technologies: " +
//         // techList.toString() + " from project " + project.getProj_name() + ".");

//         // // add the activity log
//         // activityLogProjectTechnology.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//         // activityLogDao.addActivityLog(activityLogProjectTechnology);

//         // // Acivitylog for Project Members
//         // ActivityLogInput activityLogProjectMember = new ActivityLogInput();

//         // List<String> members = new ArrayList<>();
//         // StringBuilder memberList = new StringBuilder();

//         // for (String member_id : member_ids) {
//         // UserInfoOutput member = userDao.getUserById(member_id);
//         // members.add(member.getUsername());
//         // }

//         // for (String element : members) {
//         // memberList.append("'").append(element).append("', ");
//         // }

//         // if (memberList.length() > 0) {
//         // memberList.delete(memberList.length() - 2, memberList.length());
//         // }

//         // activityLogProjectMember.setEmp_id(emp_id); // current logged user dapat
//         // activityLogProjectMember.setLog_desc("Deleted multiple members: " +
//         // memberList.toString() + " from project " + project.getProj_name() + ".");

//         // // add the activity log
//         // activityLogProjectMember.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//         // activityLogDao.addActivityLog(activityLogProjectMember);

//         // return "Project '" + project.getProj_name() + "' deleted successfully.";
//         // }
//         // } else {
//         // return "Project with the ID " + id + " cannot be found.";
//         // }
//         // }

//     }

//     @Override
//     public String restoreProject(String id) {
//         ProjectOutput project = projectDao.getProjectById(id);

//         if (project != null) {
//             if (project.getDel_flag() == 0) {
//                 return "Project with the ID " + id + " is not yet deleted.";
//             } else {
//                 // setting the del_flag of tbl_project_mst to 0 based on the selected project
//                 projectDao.restoreProject(id);
//                 // setting the del_flag of tbl_proj_info to 0 based on the selected project
//                 projInfoDao.restoreProjInfo(id);
//                 // setting the del_flag of tbl_proj_manager to 0 based on the selected project
//                 projectManagerDao.restoreProjectManager(id);
//                 // setting the del_flag of tbl_proj_phase to 0 based on the selected project
//                 projectPhaseDao.restoreProjectPhase(id);
//                 // setting the del_flag of tbl_proj_tech to 0 based on the selected project
//                 projectTechnologyDao.restoreProjectTechnology(id);
//                 // setting the del_flag of tbl_user_project to 0 based on the selected project
//                 userProjectDao.restoreUserProject(id);

//                 // Activitylog
//                 ActivityLogInput activityLogInput = new ActivityLogInput();

//                 activityLogInput.setEmp_id("101"); // current logged user dapat
//                 activityLogInput.setLog_desc("Restored '" + project.getProj_name() + "' project.");

//                 Long currentTimeMillis = System.currentTimeMillis();
//                 // add the activity log
//                 activityLogInput.setLog_date(timeFormatter.formatTime(currentTimeMillis));
//                 activityLogDao.addActivityLog(activityLogInput);

//                 return "Project '" + project.getProj_name() + "' restored successfully.";
//             }
//         } else {
//             return "Project with the ID " + id + " cannot be found.";
//         }
//     }

}