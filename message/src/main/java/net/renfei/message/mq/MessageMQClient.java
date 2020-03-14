package net.renfei.message.mq;

import net.renfei.api.account.entity.MQChannel;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 信息服务消息队列客户端
 *
 * @author RenFei
 */
public interface MessageMQClient {
    @Input(MQChannel.MESSAGE_SERVICE)
    SubscribableChannel input();
}
