package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/9 23:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Bill extends Entity {
    private String name;
    private String fee;
    private String property;
    private String payTime;
}
