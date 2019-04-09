package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 21:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Property extends Entity {

    private Integer buildings;
    private String name;
    private String code;
    private String residence;
    private String area;
    private String unit;
    private String status;
    private String doorplate;

}
