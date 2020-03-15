package net.renfei.account.service;

import net.renfei.account.client.AccountDataServiceClient;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 账户服务
 *
 * @author RenFei
 */
@Service
public class AccountService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountDataServiceClient accountDataServiceClient;

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
}
