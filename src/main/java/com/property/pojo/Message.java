package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/11 10:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Message extends Entity {
    private String creator;
    private Integer noticeId;
    private String content;
}
