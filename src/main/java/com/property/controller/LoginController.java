package com.property.controller;

import com.property.controller.result.Result;
import com.geetest.sdk.GeetestConfig;
import com.geetest.sdk.GeetestLib;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author Chichiu Yeung
 * Created in 2018/02/27 21:06
 */
@RestController
public class LoginController {

    @RequestMapping("/requestLogin")
    public Result login() {
        return Result.error("请先登录");
    }

    /**
     * 极验初始化，获取流水标识和设置状态码
     * */
    @GetMapping("/initGeetest")
    public void initGeetest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());
        String resStr;
        String userid = "test";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();
        PrintWriter out = response.getWriter();
        out.println(resStr);
    }

    /**
     * 二次验证
     * */
    @PostMapping("/verifyLogin")
    public Result verifyLogin(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        // 从session中获取gt-server状态
        Integer gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        // 从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        // 自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); // 网站用户id
        param.put("client_type", "web"); // web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); // 传输用户请求验证时所携带的IP

        int gtResult;
        if (gt_server_status_code == 1) {
            // gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        if (gtResult == 1) {

//            if (adminService.findAdminByIdAndPwd(userName, userPwd)) {
//                // 生成一个token用于验证登录状态
//                Long millis = System.currentTimeMillis();
//                String token = Md5.digest(millis.toString());
//                result = Result.success("登录成功")
//                        .add("login_status", "success")
//                        .add("token", token);
//                request.getSession().setAttribute("token", token);
//            } else {
//                result = Result.error("用户名或密码错误")
//                        .add("login_status", "fail");
//            }
            // 返回版本信息
            return Result.success().add("version", gtSdk.getVersionInfo());
        } else {
            return Result.error("验证失败");
        }
    }
}
