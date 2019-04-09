package com.property.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 17:34
 */
@Data
public class Experience {

    private Integer id;
    private Integer resumeId;
    private Integer rowNum;
    private String beginDate;
    private String endDate;
    private String company;
    private String timeStamp;
    private String post;
    private String description;
    private String type;

}
