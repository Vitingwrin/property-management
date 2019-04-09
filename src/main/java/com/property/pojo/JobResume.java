package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/4 11:15
 */
@Data
public class JobResume {

    Integer id;
    String job;
    String resume;
    String company;
    String realName;
    String timeStamp;
    String createTime;
    Integer step;
}
