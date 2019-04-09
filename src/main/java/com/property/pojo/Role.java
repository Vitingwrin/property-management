package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2018/02/27 20:34
 */
@Data
public class Role {

    public static final Integer ROLE_SUPER = 1;
    public static final Integer ROLE_ADMIN = 2;
    public static final Integer ROLE_USER = 3;

    private Integer id;
    private String name;
    private String nameZh;

}
