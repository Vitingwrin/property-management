package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 18:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Residence extends Entity {
    private String name;
    private String code;
    private Integer buildingsNum;
}
