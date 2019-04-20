package com.example.votedemo.service;

import com.example.votedemo.entity.CheerLeadingEntity;
import com.example.votedemo.entity.StudentEntity;
import com.example.votedemo.entity.UserVoteEntity;
import com.example.votedemo.mapper.CheerLeadingMapper;
import com.example.votedemo.mapper.StudentMapper;
import com.example.votedemo.mapper.UserVoteMapper;
import com.example.votedemo.mapper.VoteRecordingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 10:09.
 */

@Service
public class VoteService {
    @Autowired
    VoteRecordingMapper voteRecordingMapper;
    @Autowired
    UserVoteMapper userVoteMapper;
    @Autowired
    CheerLeadingMapper cheerLeadingMapper;

    public boolean checkVote(String openid,String college){
        Date today = new Date();
        UserVoteEntity userVoteEntity = userVoteMapper.getUser(openid);
        if(cheerLeadingMapper.checkCheerleading(college)) {
            if (userVoteEntity.voteNum> 0) {
                if (college.equals(userVoteEntity.xh)) {
                    cheerLeadingMapper.updateLocal(college);
                    voteRecordingMapper.addRecording(openid, college, new Timestamp(today.getTime() + 8 * 60 * 60 * 1000));
                    userVoteMapper.updateVoteNum(userVoteEntity.xh);
                    return true;
                } else {
                    cheerLeadingMapper.updateOther(college);
                    voteRecordingMapper.addRecording(openid, college, new Timestamp(today.getTime() + 8 * 60 * 60 * 1000));
                }
                userVoteMapper.updateVoteNum(userVoteEntity.xh);
                return true;
            }
        }
        return false;
    }
    public Map<String,Integer> getVoteEntity(){
        Map<String,Integer> result = new HashMap<>();
        for (CheerLeadingEntity entity : cheerLeadingMapper.getCheer()){
            result.put(entity.college,(1000*entity.localVote + 10*entity.otherVote));
        }
        return result;
    }
}
