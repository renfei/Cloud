package net.renfei.datacenter.controller;

import net.renfei.api.datacenter.entity.SecretKeyDTO;
import net.renfei.api.datacenter.entity.TokenDTO;
import net.renfei.api.datacenter.service.AccountDataService;
import net.renfei.datacenter.service.AccountService;
import net.renfei.datacenter.service.SecretKeyService;
import net.renfei.datacenter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RenFei
 */
@RestController
public class AccountDataImpl implements AccountDataService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecretKeyService secretKeyService;

    @Override
    public String getAccountIdById(String uuid) {
        return accountService.getAccountIdByToken(uuid);
    }

    @Override
    public int saveToken(@RequestBody TokenDTO tokenDTO) {
        return tokenService.saveToken(tokenDTO);
    }

    @Override
    public TokenDTO getToken(String tokenD) {
        return tokenService.getToken(tokenD);
    }

    @Override
    public int saveSecretKey(@RequestBody SecretKeyDTO secretKeyDTO) {
        return secretKeyService.save(secretKeyDTO);
    }

    @Override
    public String getSecretKey(String uuid) {
        return secretKeyService.getPrivateKey(uuid);
    }
}
