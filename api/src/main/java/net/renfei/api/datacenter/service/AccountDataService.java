package net.renfei.api.datacenter.service;

import net.renfei.api.datacenter.entity.SecretKeyDTO;
import net.renfei.api.datacenter.entity.TokenDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 账户数据服务
 *
 * @author RenFei
 */
public interface AccountDataService {
    /**
     * 根据用户ID查验用户是否有效
     *
     * @return
     */
    @GetMapping("/data/accountbytoken")
    String getAccountIdById(String uuid);

    @PostMapping("/data/savetoken")
    int saveToken(TokenDTO tokenDTO);

    @GetMapping("/data/token")
    TokenDTO getToken(String tokenD);

    @PostMapping("/data/savesecretkey")
    int saveSecretKey(SecretKeyDTO secretKeyDTO);
}
