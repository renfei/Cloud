package net.renfei.account.service;

import net.renfei.account.client.AccountDataServiceClient;
import net.renfei.api.datacenter.entity.SecretKeyDTO;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.Builder;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

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

    public Map<Integer, String> secretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(1024);
        if (BeanUtils.isEmpty(map)) {
            return null;
        }
        //TODO 保存
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
}
