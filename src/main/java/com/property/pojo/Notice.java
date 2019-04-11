package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/11 9:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Notice extends Entity {
    private String title;
    private String content;
    private String creator;
    List<Message> messages;
}
