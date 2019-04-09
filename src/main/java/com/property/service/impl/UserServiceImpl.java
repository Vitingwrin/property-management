package com.property.service.impl;

import com.property.mapper.UserMapper;
import com.property.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.property.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/2/28 11:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return mapper.loadUserByUsername(username);
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        return !mapper.isUsernameExists(username);
    }

    @Override
    public int insertUser(User user) {
        return mapper.insertUser(user);
    }

    @Override
    public int updatePwd(@Param("username") String username, @Param("newPwd") String newPwd) {
        return mapper.updatePwd(username, newPwd);
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        return mapper.getUserIdByUsername(username);
    }

    @Override
    public Integer getAdminCount() {
        return mapper.getAdminCount();
    }

    @Override
    public List<User> getAdminsByPaging(Integer page) {
        return mapper.getAdminsByPaging((page - 1) * 10);
    }

    @Override
    public List<User> getUsersByPaging(Integer page) {
        return mapper.getUsersByPaging((page - 1) * 10);
    }

    @Override
    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public int deleteUser(Integer userId) {
        int left = mapper.deleteUserRole(userId);
        int right = mapper.deleteUser(userId);
        return left & right;
    }

    @Override
    public int insertUserRole(Integer userId, Integer roleId) {
        return mapper.insertUserRole(userId, roleId);
    }
}
