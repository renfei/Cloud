package net.renfei.message.controller;

import lombok.extern.slf4j.Slf4j;
import net.renfei.api.message.entity.Sms;
import net.renfei.api.message.service.MessageService;
import net.renfei.message.service.SmsService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
