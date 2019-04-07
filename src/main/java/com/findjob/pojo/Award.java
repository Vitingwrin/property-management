package com.findjob.pojo;

import lombok.Data;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 17:32
 */
@Data
public class Award {
    private Integer id;
    private Integer resumeId;
    private Integer rowNum;
    private String date;
    private String name;
    private String timeStamp;
}
