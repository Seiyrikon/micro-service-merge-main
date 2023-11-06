package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.UserInfoOutput;
import com.example.demo.model.UserOutput;

@Mapper
public interface UserDao {
  // List<UserTable> userTable();

  // List<UserOutput> getAllUser();

  UserOutput getAllUser();

  UserOutput getByUsername(String username);

  UserInfoOutput getUserById(String id);

  // get multiple user using request paran
  List<UserInfoOutput> getMultipleUserById(String id);

  // void insertUser(UserInput user);
  // void editUser(HashMap<String, Object> paramMap);
  // void insertUserBatch(List<User> users);

  // setting the del_flag of user to 1
  // void logicalDeleteUser(String id);

  // setting the del_flag of user to 0
  // void restoreUser(String id);

  // UserOutput getUserByUsername(UserOutput username);
  // UserOutput getUsername(String username);

  // get all roles of a user
  // List<UserRoles> getAllRolesOfUser(String emp_id);

  // Login session
  // UserOutput getByUsername(String username);

  // batch delete
  // void deleteMultipleUser(@Param("ids") List<String> ids);

  // List<UserInfoOutput> getAllPossibleManager();

  // void changePassword(String user_id, String new_password);

  // void updateUserPicture(UserInfoOutput userOutput);

  // UserInfoOutput principalInfo(String emp_id);

}
