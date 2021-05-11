package com.zhenglei.rocketmq.mq;

import com.zhenglei.rocketmq.entity.Term;
import com.zhenglei.rocketmq.mapper.TermMapper;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.apache.rocketmq.client.producer.LocalTransactionState.*;

public class TransactionListenerImpl implements TransactionListener {
    @Autowired
    private TermMapper termMapper;

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        Integer termId = (Integer) arg;
        System.out.println("executeLocalTransaction termId=" + termId);

        return UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String termId = msg.getKeys();
        Term term = termMapper.selectByPrimaryKey(Integer.parseInt(termId));
        System.out.println("checkLocalTransaction termId=" + termId + " term:" + term);
        if (term != null) {
            System.out.println("checkLocalTransaction：COMMIT_MESSAGE" + " 时间：" + new Date());
            return COMMIT_MESSAGE;
        }
        System.out.println("checkLocalTransaction：ROLLBACK_MESSAGE" + " 时间：" + new Date());
        return ROLLBACK_MESSAGE;
    }
}