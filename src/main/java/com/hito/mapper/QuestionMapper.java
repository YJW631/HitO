package com.hito.mapper;

import com.hito.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    
    @Insert("insert into question (title,description,tag,creator,create_time,update_time) values (#{title},#{description},#{tag},#{creator},#{createTime},#{updateTime});")
    void create(Question question);
}
