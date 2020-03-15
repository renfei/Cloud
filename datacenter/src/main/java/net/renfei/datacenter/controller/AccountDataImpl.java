package net.renfei.datacenter.controller;

import net.renfei.api.datacenter.service.AccountDataService;
import net.renfei.datacenter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RenFei
 */
@RestController
public class AccountDataImpl implements AccountDataService {
    @Autowired
    private AccountService accountService;

    @Override
    public String getAccountIdById(String uuid) {
        return accountService.getAccountIdByToken(uuid);
    }
}
