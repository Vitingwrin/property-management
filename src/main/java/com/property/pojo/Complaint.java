package com.property.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 10:39
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Complaint extends Entity {
    private String title;
    private String content;
    private String creator;
    private String feedbackUser;
    private String feedbackContent;
    private String feedbackTime;
}
