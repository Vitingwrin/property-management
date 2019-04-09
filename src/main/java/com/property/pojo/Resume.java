package com.property.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 17:28
 */
@Data
public class Resume {
    private Integer id;
    private String timeStamp;
    private Integer userId;
    private String identityNum;
    private String name;
    private String sex;
    private String nation;
    private String marriage;
    private Integer workYear;
    private String address;
    private String evaluation;
    private String birthday;
    List<Award> awards;
    List<Education> educations;
    List<Experience> experiences;
}
