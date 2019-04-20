package com.example.votedemo.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 20:39.
 */

@Configuration
public class UTCTimeZoneConfiguration implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}
