package com.example.votedemo.mapper;

import com.example.votedemo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:56.
 */

@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from student where xh = #{xh}")
    StudentEntity getStudent(String xh);
}
