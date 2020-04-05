package com.zy.community.dto;

import com.zy.community.model.User;
import lombok.Data;

/**
 * @author: Yu Zhang
 * @create: 2020-04-05 17:39
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
