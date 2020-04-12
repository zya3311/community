package com.zy.community.service;

import com.zy.community.mapper.UserMapper;
import com.zy.community.model.User;
import com.zy.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yu Zhang
 * @create: 2020-04-08 21:02
 */
@Service
public class UserServcie {

    @Autowired
    private UserMapper userMapper;

    public int createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(null != users && 0 != users.size()) {
            //更新
            User dbUser = users.get(0);
            User updatedUser = new User();
            updatedUser.setGmtModified(System.currentTimeMillis());
            updatedUser.setAvatarUrl(user.getAvatarUrl());
            updatedUser.setName(user.getName());
            updatedUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            return userMapper.updateByExampleSelective(updatedUser, example);
        } else {
            // 插入
            return userMapper.insert(user);
        }
    }
}
