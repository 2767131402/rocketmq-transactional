package com.zhenglei.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zhenglei.rocketmq.mapper")
public class RocketmqTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqTransactionalApplication.class, args);
    }

}
