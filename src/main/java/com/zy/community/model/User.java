package com.zy.community.model;

import lombok.Data;

/**
 * @author: Yu Zhang
 * @create: 2020-04-03 23:36
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
