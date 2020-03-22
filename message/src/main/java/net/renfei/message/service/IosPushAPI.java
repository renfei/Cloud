package net.renfei.message.service;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import net.renfei.message.config.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

/**
 * iOS 通知推送接口
 *
 * @author RenFei
 */
@Service
public class IosPushAPI {
    @Autowired
    private MessageConfig messageConfig;

    private ApnsService initApnsService() {
        ApnsService service = null;
        String certName = messageConfig.getSandbox() ? "sandbox_certificate.p12" : "production_certificate.p12";
        try {
            service = APNS.newService()
                    .withCert(IosPushAPI.class.getResource("/apple/" + certName).openStream(), messageConfig.getIosPushAPIPassword())
                    .withAppleDestination(messageConfig.getSandbox())
                    .build();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return service;
    }

    public void sendMessageByDeviceToken(String deviceToken, String title, String content) {
        ApnsService service = this.initApnsService();
        String payload = APNS.newPayload()
                .alertTitle(title)
                .alertBody(content)
                .build();
        service.push(deviceToken, payload);
    }

    public void sendMessageByDeviceTokens(Collection<String> deviceTokens, String title,
                                          String content) {
        ApnsService service = this.initApnsService();
        String payload = APNS.newPayload()
                .alertTitle(title)
                .alertBody(content)
                .build();
        service.push(deviceTokens, payload);
    }
}
