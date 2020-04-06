package com.zy.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Yu Zhang
 * @create: 2020-04-06 12:24
 */
@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
