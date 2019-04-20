package com.example.votedemo.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/4/19.
 * @time 19:31.
 */

@Data
public class LeadingVoteEntity {
    public String voteNum;
    public Map<String,Integer> result;
}
