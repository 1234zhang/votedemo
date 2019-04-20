package com.example.votedemo.controller;

import com.example.votedemo.entity.OpenidEntity;
import com.example.votedemo.service.UserService;
import com.example.votedemo.service.WxService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Brandon.
 * @date 2019/4/15.
 * @time 13:02.
 */

@RestController
public class WxController {
    private static final String TOKEN = "test";
    private Gson gson = new Gson();

    @Autowired
    WxService wxService;
    @Autowired
    UserService userService;

    @RequestMapping("/connect")
    public String connect(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String sortString = wxService.sort(TOKEN, timestamp, nonce);
        String mySignature = wxService.sha1(sortString);
        if(mySignature != null && mySignature != "" && mySignature.equals(signature)){
            return echostr;
        }
        return null;
    }
    @RequestMapping("/redirect")
    public void redirect(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
        .append("wxda9480a7b9967800").append("&redirect_uri=").append(URLEncoder.encode("http://a24f5fd7.ngrok.io/login","utf-8"));
        sb.append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
        try {
            response.sendRedirect(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        String openid = "";

        openid = wxService.getOpenid(request,response,code);
        OpenidEntity openidEntity = gson.fromJson(openid, OpenidEntity.class);
        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession();
        }
        session.setAttribute("openid",openidEntity.openid);
        response.sendRedirect("http://a24f5fd7.ngrok.io/index.html");
    }
    @PostMapping("/info")
    public void info(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String xh = request.getParameter("xh");
        HttpSession session = request.getSession();
        if(userService.handleUser((String) session.getAttribute("openid"),xh)){
            response.sendRedirect("http://a24f5fd7.ngrok.io/index.html");
        }
    }
}