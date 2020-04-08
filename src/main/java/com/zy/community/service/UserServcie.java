package com.zy.community.service;

import com.zy.community.mapper.UserMapper;
import com.zy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Yu Zhang
 * @create: 2020-04-08 21:02
 */
@Service
public class UserServcie {

    @Autowired
    private UserMapper userMapper;

    public int createOrUpdate(User user) {
        if(null == userMapper.findByAccountId(user.getAccountId())) {
            // 插入
            return userMapper.insert(user);
        } else {
            // 更新
            return userMapper.update(user);
        }
    }
}
