package com.example.votedemo.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 13:19.
 */
@Data
public class OpenidEntity {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_in")
    public int expiresIn;
    @SerializedName("refresh_token")
    public String refreshToken;
    public String openid;
    public String scope;
}
