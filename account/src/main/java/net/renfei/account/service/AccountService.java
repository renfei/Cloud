package net.renfei.account.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import net.renfei.account.client.AccountDataServiceClient;
import net.renfei.api.account.entity.ReportPublicKeyVO;
import net.renfei.api.account.entity.SignInVO;
import net.renfei.api.datacenter.entity.SecretKeyDTO;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 账户服务
 *
 * @author RenFei
 */
@Slf4j
@Service
public class AccountService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountDataServiceClient accountDataServiceClient;

    /**
     * 登陆逻辑
     *
     * @param signInVO
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackSignIn")
    public APIResult<String> signIn(SignInVO signInVO) {
        //先获取秘钥
        String key = accountDataServiceClient.getSecretKey(signInVO.getSecretKeyId());
        if (BeanUtils.isEmpty(key)) {
            return APIResult.builder()
                    .code(StateCode.BadRequest)
                    .message("secretKeyId不正确")
                    .data("secretKeyId不正确")
                    .build();
        }
        //解密密码
        String passwd;
        try {
            passwd = RSAUtils.decrypt(signInVO.getPasswd(), key);
        } catch (Exception ex) {
            return APIResult.builder()
                    .code(StateCode.BadRequest)
                    .message("密码解密失败")
                    .data("密码解密失败")
                    .build();
        }
        if (StringUtils.isEmail(signInVO.getName())) {
            //邮箱登陆
            return signInByEmail(signInVO, passwd);
        } else if (StringUtils.isChinaPhone(signInVO.getName())) {
            //手机登陆
            return signInByPhone(signInVO, passwd);
        } else {
            //用户名登陆
            return signInByUserName(signInVO, passwd);
        }
    }

    private APIResult<String> signInByEmail(SignInVO signInVO, String passwd) {
        //根据Email查询用户，不存在就注册
        return null;
    }

    private APIResult<String> signInByPhone(SignInVO signInVO, String passwd) {
        //根据手机查询用户，不存在就注册
        return null;
    }

    private APIResult<String> signInByUserName(SignInVO signInVO, String passwd) {
        //根据用户名查询用户
        return null;
    }

    @HystrixCommand(fallbackMethod = "fallbackSignIn")
    public String getAccountIdByToken(String token) {
        String uuid = jwtService.readToken(token);
        if (!BeanUtils.isEmpty(uuid)) {
            String uuidByDb = accountDataServiceClient.getAccountIdById(uuid);
            if (!BeanUtils.isEmpty(uuidByDb)) {
                return uuidByDb;
            }
        }
        return null;
    }

    @HystrixCommand(fallbackMethod = "fallbackSignIn")
    public Map<Integer, String> secretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(1024);
        if (BeanUtils.isEmpty(map)) {
            return null;
        }
        //保存
        String uuid = UUID.randomUUID().toString();
        SecretKeyDTO secretKeyDTO = Builder.of(SecretKeyDTO::new)
                .with(SecretKeyDTO::setId, uuid)
                .with(SecretKeyDTO::setPrivateKey, map.get(1))
                .with(SecretKeyDTO::setPublicKey, map.get(0))
                .build();
        accountDataServiceClient.saveSecretKey(secretKeyDTO);
        map.put(1, uuid);
        return map;
    }

    @HystrixCommand(fallbackMethod = "fallbackSignIn")
    public APIResult setSecretKey(ReportPublicKeyVO reportPublicKeyVO) {
        String rasKey = accountDataServiceClient.getSecretKey(reportPublicKeyVO.getSecretKeyId());
        if (BeanUtils.isEmpty(rasKey)) {
            return APIResult.builder()
                    .code(StateCode.BadRequest)
                    .message("secretKeyId不正确")
                    .build();
        }
        String clentKey = null;
        try {
            clentKey = RSAUtils.decrypt(reportPublicKeyVO.getPublicKey(), rasKey);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return APIResult.builder()
                    .code(StateCode.BadRequest)
                    .message("publicKey解密失败")
                    .build();
        }
        String aes = RandomStringUtils.getRandomString(16);
        String aesEnc;
        try {
            aesEnc = RSAUtils.encrypt(aes, clentKey);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return APIResult.builder()
                    .code(StateCode.Error)
                    .message("服务器内部错误，使用RSA客户端公钥加密失败")
                    .build();
        }
        //保存AES
        String uuid = UUID.randomUUID().toString();
        SecretKeyDTO secretKeyDTO = Builder.of(SecretKeyDTO::new)
                .with(SecretKeyDTO::setId, uuid)
                .with(SecretKeyDTO::setPrivateKey, aes)
                .build();
        accountDataServiceClient.saveSecretKey(secretKeyDTO);
        Map<String, String> map = new HashMap<>();
        map.put("keyid", uuid);
        map.put("aeskey", aesEnc);
        return APIResult.builder()
                .code(StateCode.OK)
                .message("成功！")
                .data(map)
                .build();
    }

    private APIResult fallbackSignIn() {
        return APIResult.builder()
                .code(StateCode.Error)
                .message("数据中心服务系统暂时不可用，请稍后再试")
                .build();
    }
}
