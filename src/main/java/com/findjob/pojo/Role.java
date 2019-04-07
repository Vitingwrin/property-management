package com.findjob.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2018/02/27 20:34
 */
@Data
public class Role {

    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_USER = 2;

    private Integer id;
    private String name;
    private String nameZh;

}
