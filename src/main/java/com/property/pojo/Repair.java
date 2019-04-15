package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 12:38
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Repair extends Entity {
    private String type;
    private String phone;
    private String creator;
    private String expectTime;
    private String code;
    private Integer status;
    private String fee;
    private String comment;
}
