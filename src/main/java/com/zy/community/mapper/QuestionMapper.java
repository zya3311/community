package com.zy.community.mapper;

import com.zy.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Yu Zhang
 * @create: 2020-04-04 20:19
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) " +
            "values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    /**
     * 查询从第offset+1开始的size个question items
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 查询question总数
     * @return
     */
    @Select(" select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param("userId") Long id, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select(" select count(1) from question where creator = #{userId}")
    Integer countByUserId(Long userId);
}
