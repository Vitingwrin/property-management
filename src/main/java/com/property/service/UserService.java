package com.property.service;

import com.property.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
    boolean checkUsernameUnique(String username);
    int insertUser(User user);
    int updatePwd(@Param("username") String username, @Param("newPwd") String newPwd);
    Integer getUserIdByUsername(String username);
    Integer getAdminCount();
    List<User> getAdminsByPaging(Integer page);
    List<User> getUsersByPaging(Integer page);
    List<User> getAllUsers();
    int deleteUser(Integer userId);
    int insertUserRole(Integer userId, Integer roleId);

}
