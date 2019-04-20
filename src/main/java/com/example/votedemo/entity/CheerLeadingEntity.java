package com.example.votedemo.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 10:28.
 */

@Data
public class CheerLeadingEntity {
    public String college;
    @SerializedName("local_vote")
    public int localVote;
    @SerializedName("other_vote")
    public int otherVote;
}
