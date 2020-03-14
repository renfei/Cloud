package net.renfei.message.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import net.renfei.api.message.entity.Sms;
import net.renfei.message.config.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信服务
 *
 * @author RenFei
 */
@Service
public class SmsService {
    @Autowired
    private MessageConfig messageConfig;

    public String sendSms(Sms sms) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(messageConfig.getRegionId(), messageConfig.getAccessKeyId(), messageConfig.getSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", messageConfig.getRegionId());
        request.putQueryParameter("PhoneNumbers", sms.getPhoneNumbers());
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());
        CommonResponse response = client.getCommonResponse(request);
        return response.getData();
    }
}
