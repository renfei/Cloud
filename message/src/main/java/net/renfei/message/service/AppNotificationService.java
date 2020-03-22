package net.renfei.message.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import lombok.extern.slf4j.Slf4j;
import net.renfei.api.message.entity.AppNotificationBean;
import net.renfei.message.config.MessageConfig;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * APP推送通知
 *
 * @author RenFei
 */
@Slf4j
@Service
public class AppNotificationService {
    @Autowired
    private MessageConfig messageConfig;

    public void send(AppNotificationBean appNotificationBean) {
        IClientProfile profile = DefaultProfile.getProfile(messageConfig.getRegionId(), messageConfig.getAccessKeyId(), messageConfig.getSecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        PushRequest pushRequest = new PushRequest();
        // 推送目标
        pushRequest.setAppKey(messageConfig.getAppNotificationKey());
        //推送目标: DEVICE:按设备推送 ALIAS : 按别名推送 ACCOUNT:按帐号推送  TAG:按标签推送; ALL: 广播推送
        pushRequest.setTarget(appNotificationBean.getTarget().getType());
        //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        pushRequest.setTargetValue(appNotificationBean.getTargetValue());
        // 消息类型 MESSAGE NOTICE
        pushRequest.setPushType(appNotificationBean.getPushType().getType());
        // 设备类型 ANDROID iOS ALL.
        pushRequest.setDeviceType(appNotificationBean.getDeviceType().getType());
        // 推送配置
        // 消息的标题
        pushRequest.setTitle(appNotificationBean.getTitle());
        // 消息的内容
        pushRequest.setBody(appNotificationBean.getContent());
        /******************** 推送配置: iOS ********************/
        // 角标自增功能由推送服务端维护每个设备的角标计数
        pushRequest.setIOSBadgeAutoIncrement(true);
        if (!BeanUtils.isEmpty(appNotificationBean.getSubtitle())) {
            //iOS10通知副标题的内容
            pushRequest.setIOSSubtitle(appNotificationBean.getSubtitle());
        }
        if (!BeanUtils.isEmpty(appNotificationBean.getExtParameters())) {
            //是否允许扩展iOS通知内容
            pushRequest.setIOSMutableContent(true);
            //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
            pushRequest.setIOSExtParameters(JSON.toJSONString(appNotificationBean.getExtParameters()));
        }
        //iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
        pushRequest.setIOSApnsEnv(messageConfig.getIOSApnsEnv());
        // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
        pushRequest.setIOSRemind(true);
        //iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
        pushRequest.setIOSRemindBody("iOSRemindBody");
        /******************** 推送配置: Android ********************/
        //通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
        pushRequest.setAndroidNotifyType("BOTH");
        //通知栏自定义样式0-100
        pushRequest.setAndroidNotificationBarType(1);
        //通知栏自定义样式0-100
        pushRequest.setAndroidNotificationBarPriority(1);
        //点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
        pushRequest.setAndroidOpenType(appNotificationBean.getAndroidOpenType().getType());
        switch (appNotificationBean.getAndroidOpenType()) {
            case APPLICATION:
                break;
            case ACTIVITY:
                pushRequest.setAndroidActivity(appNotificationBean.getAndroidOpenContent());
                break;
            case URL:
                pushRequest.setAndroidOpenUrl(appNotificationBean.getAndroidOpenContent());
                break;
            case NONE:
                break;
            default:
                pushRequest.setAndroidOpenType("NONE");
                break;
        }
        // Android通知音乐
        pushRequest.setAndroidMusic("default");
        if (!BeanUtils.isEmpty(appNotificationBean.getExtParameters())) {
            //设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
            pushRequest.setAndroidExtParameters(JSON.toJSONString(appNotificationBean.getExtParameters()));
        }
        // 推送控制
        // 30秒之间的时间点, 也可以设置成你指定固定时间
        Date pushDate = appNotificationBean.getPushTime();
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        // 延后推送。可选，如果不设置表示立即推送
        pushRequest.setPushTime(pushTime);
        // 12小时后消息失效, 不会再发送
        String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000));
        pushRequest.setExpireTime(expireTime);
        // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        pushRequest.setStoreOffline(true);
        try {
            PushResponse pushResponse = client.getAcsResponse(pushRequest);
            System.out.printf("RequestId: %s, MessageID: %s\n",
                    pushResponse.getRequestId(), pushResponse.getMessageId());
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
