package com.example.votedemo.mapper;

import com.example.votedemo.entity.UserVoteEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:32.
 */

@Mapper
@Repository
public interface UserVoteMapper {
    @Select("select*from vote_user where openid = #{openid}")
    @Results(
            @Result(property = "voteNum",column = "vote_num")
    )
    UserVoteEntity getUser(String openid);
    @Insert("insert ignore into vote_user(openid,xh,xym) value (#{openid},#{xh},#{xym})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    boolean addUser(String openid,String xh, String xym);
    @Update("update vote_user set vote_num = 5")
    boolean updateVote();
    @Update("update vote_user set vote_num = vote_num - 1 where xh = #{xh}")
    boolean updateVoteNum(String xh);
}
