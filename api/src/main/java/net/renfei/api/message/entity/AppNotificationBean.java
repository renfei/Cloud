package net.renfei.api.message.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author RenFei
 */
@Data
public class AppNotificationBean {
    private String title;
    private String subtitle;
    private String content;
    private String targetValue;
    private PushTargetType target;
    private PushMessageType pushType;
    private DeviceType deviceType;
    private Object extParameters;
    private Date pushTime;
    /**
     * 点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
     */
    private PushAndroidOpenType androidOpenType;
    private String androidOpenContent;
}
