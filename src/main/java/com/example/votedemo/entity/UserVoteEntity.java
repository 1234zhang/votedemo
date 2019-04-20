package com.example.votedemo.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:31.
 */

@Data
public class UserVoteEntity {
    public String openid;
    public String xh;
    public String xym;
    public int voteNum;
}
