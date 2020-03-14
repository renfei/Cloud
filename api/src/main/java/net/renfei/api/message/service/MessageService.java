package net.renfei.api.message.service;

import net.renfei.api.message.entity.Email;
import net.renfei.api.message.entity.Sms;
import net.renfei.sdk.entity.APIResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 消息服务
 *
 * @author RenFei
 */
public interface MessageService {
    /**
     * 发送短信
     *
     * @param sms
     * @return
     */
    @PostMapping("/message/sendsms")
    APIResult sendSms(Sms sms);

    /**
     * 发送电子邮件
     *
     * @param email
     * @return
     */
    @PostMapping("/message/sendemail")
    void sendEmail(Email email);
}
