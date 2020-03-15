package net.renfei.api.datacenter.service;

import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("data/accountbytoken")
    String getAccountIdById(String uuid);
}
