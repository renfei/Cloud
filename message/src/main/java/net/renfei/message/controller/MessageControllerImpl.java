package net.renfei.message.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.api.message.entity.AppNotificationBean;
import net.renfei.api.message.entity.Email;
import net.renfei.api.message.entity.Sms;
import net.renfei.api.message.service.MessageService;
import net.renfei.message.service.AppNotificationService;
import net.renfei.message.service.EmailService;
import net.renfei.message.service.SmsService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 信息服务
 *
 * @author RenFei
 */
@Slf4j
@RestController
public class MessageControllerImpl implements MessageService {
    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AppNotificationService appNotificationService;

    @Override
    public APIResult sendSms(Sms sms) {
        try {
            String response = smsService.sendSms(sms);
            return APIResult.builder()
                    .code(StateCode.OK)
                    .data(response)
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return APIResult.builder()
                    .code(StateCode.Error)
                    .build();
        }
    }

    @Override
    public void sendEmail(Email email) {
        try {
            emailService.sendEmailWithHtml(email);
        } catch (MessagingException me) {
            log.error(me.getMessage(), me);
        }

    }

    @Override
    public void sendAppNotification(AppNotificationBean appNotificationBean) {
        appNotificationService.send(appNotificationBean);
    }
}
