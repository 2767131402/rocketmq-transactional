package com.zhenglei.rocketmq;

import com.zhenglei.rocketmq.service.TermService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RocketmqTransactionalApplicationTests {

    @Autowired
    private TermService termService;

    @Test
    void contextLoads() {
    }

    @Test
    public void sendTransactionMQ() throws InterruptedException {
        try {
            termService.sendTransactionMQ(9);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(600000);
    }

}
