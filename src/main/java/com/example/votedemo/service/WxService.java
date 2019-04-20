package com.example.votedemo.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Brandon.
 * @date 2019/4/14.
 * @time 22:17.
 */

@Service
public class WxService {
    public String sort(String token,String timestamp, String nonce){
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: strArray) {
            stringBuilder.append(str);

        }
        return stringBuilder.toString();
    }
    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getOpenid(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
        String openid = "";
        StringBuffer sb = new StringBuffer();

        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append("wxda9480a7b9967800")
                .append("&secret=47f499d6241f13c28fdc4be364a9c5db").append("&code=").append(code)
                .append("&grant_type=authorization_code");
        URL url = new URL(sb.toString());
        HttpURLConnection content = (HttpURLConnection) url.openConnection();
        content.setRequestMethod("GET");
        InputStream inputStream = content.getInputStream();
        String userInfo = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        return userInfo;
    }
}
