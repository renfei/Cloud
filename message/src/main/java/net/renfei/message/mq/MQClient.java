package net.renfei.message.mq;

import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import net.renfei.api.account.entity.MQChannel;
import net.renfei.api.message.entity.AppNotificationBean;
import net.renfei.api.message.entity.Email;
import net.renfei.api.message.entity.Sms;
import net.renfei.message.service.AppNotificationService;
import net.renfei.message.service.EmailService;
import net.renfei.message.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * 消息队列监听客户端
 *
 * @author RenFei
 */
@Slf4j
@Component
@EnableBinding({MessageMQClient.class})
public class MQClient {
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private AppNotificationService appNotificationService;

    @StreamListener(MQChannel.MESSAGE_SERVICE)
    public void process(Object message) throws MessagingException, ClientException {
        if (message instanceof Sms) {
            smsService.sendSms((Sms) message);
        } else if (message instanceof Email) {
            emailService.sendEmailWithHtml((Email) message);
        } else if (message instanceof AppNotificationBean) {
            appNotificationService.send((AppNotificationBean) message);
        }
    }
}
