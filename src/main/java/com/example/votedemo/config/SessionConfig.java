package com.example.votedemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Brandon.
 * @date 2019/4/16.
 * @time 13:51.
 */

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
}
