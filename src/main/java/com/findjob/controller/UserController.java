package com.findjob.controller;

import com.findjob.controller.result.Result;
import com.findjob.pojo.Role;
import com.findjob.pojo.User;
import com.findjob.service.UserService;
import com.findjob.util.Md5;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Objects;

/**
 * @author Chichiu Yeung
 * Created in 2019/2/28 10:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkUsernameUnique")
    public Result checkUsernameUnique(@Param("username") String username) {
        return userService.checkUsernameUnique(username) ? Result.success() : Result.error("用户已存在");
    }

    @PostMapping("/addAdmin")
    public Result addAdmin(User user) {
        user.setPassword(Md5.digest(user.getPassword()));
        try {
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), Role.ROLE_USER);
            userService.insertUserRole(user.getId(), Role.ROLE_ADMIN);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
        return Result.success("用户添加成功");
    }

    @PostMapping("/addUser")
    public Result addUser(User user) {
        user.setPassword(Md5.digest(user.getPassword()));
        try {
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), Role.ROLE_USER);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
        return Result.success("注册成功");
    }

    @PostMapping("/changePwd")
    public Result changePwd(@Param("username") String username, @Param("newPwd") String newPwd) {
        newPwd = Md5.digest(newPwd);
        try {
            if (userService.updatePwd(username, newPwd) != 1) {
                return Result.error("修改密码失败（数据库出错）");
            }
        }catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
        return Result.success("密码修改成功");
    }

    @PostMapping("/validatePwd")
    public Result validatePwd(@Param("username") String username, @Param("password") String password) {
        User user = (User) userService.loadUserByUsername(username);
        return Objects.equals(Md5.digest(password), user.getPassword()) ? Result.success() : Result.error("密码错误");
    }

    @GetMapping("/getAdminsByPaging")
    public Result getAdminsByPaging(@RequestParam("page") Integer page) {
        Result result = Result.success();
        try {
            result.add("total", userService.getAdminCount());
            result.add("admins", userService.getAdminsByPaging(page));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
        return result;
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam("id") Integer id) {
        return userService.deleteUser(id) == 1 ? Result.success("用户删除成功") : Result.error("删除失败，请重试");
    }
}
