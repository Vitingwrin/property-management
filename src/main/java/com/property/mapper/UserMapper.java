package com.property.mapper;

import com.property.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/2/28 11:00
 */
@Component
public interface UserMapper {

    UserDetails loadUserByUsername(String username);
    boolean isUsernameExists(String username);
    int insertUser(User user);
    int updatePwd(@Param("username") String username, @Param("newPwd") String newPwd);
    Integer getUserIdByUsername(String username);
    List<User> getAdminsByPaging(Integer offset);
    List<User> getUsersByPaging(Integer offset);
    List<User> getAllUsers();
    Integer getAdminCount();
    int deleteUserRole(Integer id);
    int deleteUser(Integer id);
    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}
