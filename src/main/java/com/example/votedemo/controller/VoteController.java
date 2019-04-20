package com.example.votedemo.controller;

import com.example.votedemo.entity.LeadingVoteEntity;
import com.example.votedemo.entity.UserVoteEntity;
import com.example.votedemo.mapper.UserVoteMapper;
import com.example.votedemo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:11.
 */

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteService voteService;
    @Autowired
    UserVoteMapper userVoteMapper;

    @RequestMapping("/vote")
    public <T> T vote(HttpServletRequest request, HttpServletResponse response){
        String openid = (String)request.getSession().getAttribute("openid");
        String college = request.getParameter("college");
        LeadingVoteEntity leadingVoteEntity = new LeadingVoteEntity();
        if(voteService.checkVote(openid,college)){
            leadingVoteEntity.result = voteService.getVoteEntity();
            leadingVoteEntity.voteNum = "还剩投票数：" + userVoteMapper.getUser(openid).voteNum;
            return (T) leadingVoteEntity;
        }else{
            return (T) "投票失败，您没有多余的票";
        }
    }
}
