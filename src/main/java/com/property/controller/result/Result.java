package com.property.controller.result;

import java.util.HashMap;

/**
 * 服务器返回结果规范
 * @author Chichiu Yeung
 * Created in 2019/02/27 10:42
 */
public class Result extends HashMap<String, Object> {

    private static final Integer STATUS_SUCCESS = 200;
    private static final Integer STATUS_ERROR = 500;
    public static final String DATABASE_ERROR = "数据库出错，请查看服务器日志";

    private Result(Integer status) {
        this(status, null);
    }
    private Result(Integer status, String message) {
        put("status", status);
        put("message", message);
    }

    public static Result success() {
        return new Result(STATUS_SUCCESS);
    }

    public static Result success(String message) {
        return new Result(STATUS_SUCCESS, message);
    }

    public static Result error(String message) {
        return new Result(STATUS_ERROR, message);
    }

    public Result add(String key, Object value) {
        put(key, value);
        return this;
    }
}
