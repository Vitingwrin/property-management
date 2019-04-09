package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/16 23:12
 */
@Data
public class Company {

    private Integer id;
    private String timeStamp;
    private String name;
    private String address;
    private String email;
    private String intro;
    private String type;
    private String creator;

}
