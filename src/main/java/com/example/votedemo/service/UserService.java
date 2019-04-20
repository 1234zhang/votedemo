package com.example.votedemo.service;

import com.example.votedemo.entity.StudentEntity;
import com.example.votedemo.mapper.StudentMapper;
import com.example.votedemo.mapper.UserVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 15:38.
 */

@Service
public class UserService {
    @Autowired
    UserVoteMapper userVoteMapper;
    @Autowired
    StudentMapper studentMapper;

    public boolean handleUser(String openid, String xh){
        StudentEntity studentEntity = studentMapper.getStudent(xh);
        return userVoteMapper.addUser(openid,studentEntity.xh,studentEntity.yxm);
    }
}
