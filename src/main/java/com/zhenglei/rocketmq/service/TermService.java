package com.zhenglei.rocketmq.service;

import com.zhenglei.rocketmq.entity.Term;
import com.zhenglei.rocketmq.mapper.TermMapper;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TermService {
    @Autowired
    private TermMapper termMapper;
    @Autowired
    @Qualifier("transactionProducer")
    private TransactionMQProducer producer;

    @Transactional(rollbackFor = Exception.class)
    public void sendTransactionMQ(int id) throws Exception {
        Term term = new Term();
        term.setTermYear(2020);
        term.setType(1);
        term.setId(id);
        int insert = termMapper.insert(term);
        System.err.println("插入数据库完成");

        Message message = new Message();
        message.setTopic("cluster-topic");
        message.setKeys(term.getId() + "");
        message.setBody(new String("this is transaction mq " + new Date()).getBytes());

        TransactionSendResult sendResult = producer.sendMessageInTransaction(message, term.getId());
        System.err.println("sendResult:" + sendResult.getLocalTransactionState() + " 时间：" + new Date());
        throw new Exception("自定义异常");
    }
}
