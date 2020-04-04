package com.zy.community.dto;

import lombok.Data;


/**
 * @author yu
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
