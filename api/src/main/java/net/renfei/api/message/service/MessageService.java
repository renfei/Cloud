package net.renfei.api.message.service;

import net.renfei.api.message.entity.AppNotificationBean;
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
    @PostMapping("/sendsms")
    APIResult sendSms(Sms sms);

    /**
     * 发送电子邮件
     *
     * @param email
     * @return
     */
    @PostMapping("/sendemail")
    void sendEmail(Email email);

    /**
     * 发送APP通知
     *
     * @param appNotificationBean
     * @return
     */
    @PostMapping("/sendapp")
    void sendAppNotification(AppNotificationBean appNotificationBean);
}
