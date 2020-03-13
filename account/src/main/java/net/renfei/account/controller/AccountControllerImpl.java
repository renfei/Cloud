package net.renfei.account.controller;

import net.renfei.account.service.AccountService;
import net.renfei.api.account.entity.SignInVO;
import net.renfei.api.account.entity.SignUpVO;
import net.renfei.api.account.service.AccountIdService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户服务
 *
 * @author RenFei
 */
@RestController
public class AccountControllerImpl implements AccountIdService {
    @Autowired
    private AccountService accountService;

    @Override
    public APIResult<String> signIn(@RequestBody SignInVO signInVO) {
        return null;
    }

    @Override
    public APIResult<String> signUp(SignUpVO signUpVO) {
        return null;
    }

    @Override
    public APIResult<String> getAccountIdByToken(String token) {
        String uuid = accountService.getAccountIdByToken(token);
        if (!BeanUtils.isEmpty(uuid)) {
            return APIResult.builder()
                    .code(StateCode.OK)
                    .data(uuid)
                    .build();
        }
        return APIResult.builder()
                .code(StateCode.Unauthorized)
                .build();
    }
}
