package net.renfei.message.service;

import org.springframework.stereotype.Service;

/**
 * 华为通知推送API
 *
 * @author RenFei
 */
@Service
public class HuaWeiPushAPI {
//    private NSPClient initOAuth2Client() {
//        return null;
//    }

    /**
     * 通知栏消息接口.
     */
    public void sendMessage(Integer pushType, String devideTokens, String title, String content) {
        //必选 1：指定用户，必须指定tokens字段 2：所有人，无需指定tokens，tags，exclude_tags  3：一群人，必须指定tags或者exclude_tags字段
    }
}
