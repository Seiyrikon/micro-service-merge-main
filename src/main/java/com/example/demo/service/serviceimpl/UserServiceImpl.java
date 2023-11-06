package com.example.demo.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.UserInfoOutput;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    public UserDao userInfoDao;

    @Override
    public UserInfoOutput getUserById(String id) {

        UserInfoOutput user = userInfoDao.getUserById(id);

        // List<String> userRoleLevelList = new ArrayList<>();

        Map<String, Object> role_user_level = new HashMap<>();
        Map<String, Object> user_multi_role = new HashMap<>();
        Map<String, Object> project_names = new HashMap<>();
        // Map<String, Object> project_status_names = new HashMap<>();

        String[] role_levels = user.getRole_user_level_string().split(",");
        String[] role_titles = user.getUser_multi_role_string().split(",");
        String[] proj_names = user.getProj_name_string().split(",");
        // String[] proj_status_names = user.getProj_status_name_string().split(",");

        // Create a list to store the objects
        List<String> forRoleLevels = new ArrayList<>();
        List<String> forRoleTitles = new ArrayList<>();
        List<String> forProjectNames = new ArrayList<>();
        // List<String> forProjectStatusNames = new ArrayList<>();
        // List<String> projects = userInfoDao.getUserProject(id);

        // Convert the substrings to integers and add them to the list
        for (String part : role_levels) {
            try {
            forRoleLevels.add(part);
            } catch (NumberFormatException e) {
            // Handle parsing errors if necessary
            System.err.println("Failed to parse: " + part);
            }
        }

        for (String part : role_titles) {
            try {
            forRoleTitles.add(part);
            } catch (NumberFormatException e) {
            // Handle parsing errors if necessary
            System.err.println("Failed to parse: " + part);
            }
        }
        for (String part : proj_names) {
            try {
            forProjectNames.add(part);
            } catch (NumberFormatException e) {
            // Handle parsing errors if necessary
            System.err.println("Failed to parse: " + part);
            }
        }

        // for (String part : proj_status_names) {
        //     try {
        //     forProjectStatusNames.add(part);
        //     } catch (NumberFormatException e) {
        //     // Handle parsing errors if necessary
        //     System.err.println("Failed to parse: " + part);
        //     }
        // }

        role_user_level.put("role_user_level", forRoleLevels);
        user_multi_role.put("user_multi_role", forRoleTitles);
        project_names.put("proj_name", forProjectNames);
        // project_status_names.put("proj_status", forProjectStatusNames);
        // for(String userRoleLevel : user.getRole_user_level()) {
        // userRoleLevelList.add(userRoleLevel);
        // }

        // user.setRole_user_level(userRoleLevelList);
        user.setRole_user_level(role_user_level);
        user.setUser_multi_role(user_multi_role);
        user.setProj_name(project_names);
        // user.setProj_status(project_status_names);

        // System.out.println("User" + user);
        // System.out.println("User Role Level String: " +
        // user.getRole_user_level_string());
        // System.out.println("User Role Level: " + user.getRole_user_level());
        return user;
    }
}
