package com.zy.community.dto;

import lombok.Data;

/**
 * @author: Yu Zhang
 * @create: 2020-04-03 19:14
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
