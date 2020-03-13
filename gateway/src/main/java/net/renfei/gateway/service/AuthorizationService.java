package net.renfei.gateway.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import net.renfei.gateway.client.AccountServiceClient;
import net.renfei.gateway.config.GatewayConfig;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 认证服务
 *
 * @author RenFei
 */
@Service
public class AuthorizationService {
    @Autowired
    private GatewayConfig gatewayConfig;
    @Autowired
    private AccountServiceClient accountServiceClient;

    /**
     * 综合身份认证
     *
     * @param request
     * @return
     */
    public APIResult authorization(HttpServletRequest request) {
        String token = request.getHeader(gatewayConfig.getTokenCode());
        APIResult apiResult = signature(request);
        if (apiResult != null) {
            return apiResult;
        }
        //调用账户服务验证Token
        return authorizationByAccountService(token);
    }

    @HystrixCommand(fallbackMethod = "authorizationByAccountService")
    private APIResult authorizationByAccountService(String token) {
        APIResult apiResult1 = accountServiceClient.getAccountIdByToken(token);
        if (!StateCode.OK.getCode().equals(apiResult1.getCode())) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message("账号状态异常，请重新登录")
                    .build();
        }
        return null;
    }

    private APIResult authorizationByAccountService() {
        return APIResult.builder()
                .code(StateCode.Error)
                .message("账号服务系统暂时不可用，请稍后再试")
                .build();
    }

    /**
     * 签名校验
     *
     * @param request
     * @return
     */
    private APIResult signature(HttpServletRequest request) {
        String token, timestamp, signature, nonce;
        token = request.getHeader(gatewayConfig.getTokenCode());
        timestamp = request.getHeader(gatewayConfig.getTimestampCode());
        signature = request.getHeader(gatewayConfig.getSignatureCode());
        nonce = request.getHeader(gatewayConfig.getNonceCode());
        if (BeanUtils.isEmpty(token) || BeanUtils.isEmpty(timestamp) ||
                BeanUtils.isEmpty(signature) || BeanUtils.isEmpty(nonce)) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message("请求头信息不完整")
                    .build();
        }
        //TODO Redis查看签名是否被使用过
        int clientTime, serverTime = (int) (System.currentTimeMillis() / 1000), diff;
        try {
            clientTime = Integer.valueOf(timestamp);
        } catch (NumberFormatException ex) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message("时间戳异常")
                    .build();
        }
        diff = serverTime - clientTime;
        if (-30L > diff || diff > 30) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message("服务器与客户端时间差超过容错范围，请确认正确时间")
                    .build();
        }
        String[] strArr = {token, timestamp, nonce};
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        for (String param : strArr) {
            sb.append(param);
        }
        if (!EncryptionUtils.encrypt("SHA1", sb.toString()).equals(signature)) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message("签名验证不通过")
                    .build();
        }
        return null;
    }
}
