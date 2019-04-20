package com.example.votedemo.filter;

import com.example.votedemo.entity.UserVoteEntity;
import com.example.votedemo.mapper.UserVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 14:15.
 */

public class LoginFilter implements Filter {
    @Autowired
    UserVoteMapper userVoteMapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserVoteEntity userVoteEntity = userVoteMapper.getUser((String)session.getAttribute("openid"));
        if(userVoteEntity == null){
            response.sendRedirect("http://a24f5fd7.ngrok.io/login.html");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
