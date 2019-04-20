package com.example.votedemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 9:40.
 */

@Mapper
@Repository
public interface VoteRecordingMapper {
    @Insert("insert into vote_recording(openid,college,time) value (#{openid},#{college},#{time,jdbcType = TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    boolean addRecording(String openid, String college, Timestamp time);
}
