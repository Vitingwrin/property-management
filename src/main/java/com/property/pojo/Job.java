package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/20 12:00
 */
@Data
public class Job {

    private Integer id;
    private String timeStamp;
    private String createTime;
    private String creator;
    private String name;
    private Integer workYear;
    private String sex;
    private Integer requireCount;
    private Integer age1;
    private Integer age2;
    private String education;
    private String place;
    private String company;
    private String intro;

}
