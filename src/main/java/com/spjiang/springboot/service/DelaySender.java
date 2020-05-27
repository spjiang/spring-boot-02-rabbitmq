package com.spjiang.springboot.service;

import com.spjiang.springboot.bean.Order;
import com.spjiang.springboot.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Package: com.spjiang.springboot.service
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-05-27 15:35
 */
@Component
@Slf4j
public class DelaySender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDelay(Order order) {
        log.info("【订单生成时间】" + new Date().toString() + "【1分钟后检查订单是否已经支付】" + order.toString());
        this.amqpTemplate.convertAndSend(RabbitmqConfig.ORDER_DELAY_EXCHANGE, RabbitmqConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1 * 1000 * 20 + "");
            return message;
        });
    }
}
