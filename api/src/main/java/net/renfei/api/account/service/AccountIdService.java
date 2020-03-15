package net.renfei.api.account.service;

import net.renfei.api.account.entity.SignInVO;
import net.renfei.api.account.entity.SignUpVO;
import net.renfei.sdk.entity.APIResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 账户服务
 *
 * @author RenFei
 */
public interface AccountIdService {
    /**
     * 申请一个非对称秘钥
     *
     * @return
     */
    @GetMapping("/account/secretkey")
    APIResult secretKey();

    /**
     * 登录
     *
     * @param signInVO 登录请求对象
     * @return
     */
    @PostMapping("/account/signin")
    APIResult<String> signIn(SignInVO signInVO);

    /**
     * 登录
     *
     * @param signUpVO 注册请求对象
     * @return
     */
    @PostMapping("/account/signup")
    APIResult<String> signUp(SignUpVO signUpVO);

    /**
     * 根据Token获取账户ID
     *
     * @param token
     * @return
     */
    @GetMapping("/account/getAccountIdByToken")
    APIResult<String> getAccountIdByToken(String token);
}
