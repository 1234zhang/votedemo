package com.example.votedemo.scheduled;

import com.example.votedemo.mapper.UserVoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Brandon.
 * @date 2019/4/18.
 * @time 9:14.
 */

@Slf4j
@Component
public class VoteNumScheduled {
    @Autowired
    UserVoteMapper userVoteMapper;
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduled(){
        log.info("============== >>>>>>> 数据更新{}", System.currentTimeMillis() );
        userVoteMapper.updateVote();
    }
}
