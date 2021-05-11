package com.zhenglei.rocketmq.conf;

import com.zhenglei.rocketmq.mq.TransactionListenerImpl;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConf {
    @Bean(name = "transactionProducer", initMethod = "start", destroyMethod = "shutdown")
    public TransactionMQProducer transactionProducer() {
        TransactionMQProducer producer = new TransactionMQProducer("TransactionMQProducer");
        producer.setNamesrvAddr("81.70.175.128:9876;");
        producer.setSendMsgTimeout(6000);
        producer.setTransactionListener(transactionListener());
        return producer;
    }

    @Bean
    public TransactionListener transactionListener() {
        return new TransactionListenerImpl();
    }
}
