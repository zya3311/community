package com.zy.community.mapper;

import com.zy.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author: Yu Zhang
 * @create: 2020-04-03 23:34
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified},#{bio},#{avatarUrl})")
    int insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(String accountId);

    @Update("update USER set name=#{name}, token=#{token}, gmt_modified=#{gmtModified}, avatar_url=#{avatarUrl} where account_id=#{accountId}")
    int update(User user);
}
