package com.findjob.service;

import com.findjob.pojo.User;
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
    int deleteUser(Integer userId);
    int insertUserRole(Integer userId, Integer roleId);

}
