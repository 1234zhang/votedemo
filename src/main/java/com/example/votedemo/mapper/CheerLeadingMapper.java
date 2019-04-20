package com.example.votedemo.mapper;

import com.example.votedemo.entity.CheerLeadingEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 10:11.
 */

@Mapper
@Repository
public interface CheerLeadingMapper {
    @Update("update cheerleading set local_vote = local_vote + 1 where college = #{college}")
    boolean updateLocal(String college);
    @Update("update cheerleading set other_vote = other_vote + 1 where college = #{college}")
    boolean updateOther(String college);
    @Select("select * from cheerleading where (college = #{college})")
    boolean checkCheerleading(String college);
    @Select("select * from cheerleading")
    @Results({
            @Result(property = "localVote",column = "local_vote"),
            @Result(property = "otherVote",column = "other_vote")
    })
    List<CheerLeadingEntity> getCheer();
}
