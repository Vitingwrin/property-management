package com.findjob.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 17:31
 */
@Data
public class Education {
    private Integer id;
    private String resumeId;
    private Integer rowNum;
    private String timeStamp;
    private String beginDate;
    private String endDate;
    private String major;
    private String school;
    private String edu;
    private String type;
}
