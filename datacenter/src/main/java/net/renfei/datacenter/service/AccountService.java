package net.renfei.datacenter.service;

import net.renfei.datacenter.database.entity.AccountDO;
import net.renfei.datacenter.database.entity.AccountDOExample;
import net.renfei.datacenter.database.persistences.AccountDOMapper;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 账户数据服务
 *
 * @author RenFei
 */
@Service
public class AccountService {
    @Autowired
    private AccountDOMapper accountDOMapper;

    public String getAccountIdByToken(String uuid) {
        if (!BeanUtils.isEmpty(uuid)) {
            AccountDOExample accountDoExample = new AccountDOExample();
            accountDoExample.createCriteria()
                    .andUuidEqualTo(uuid)
                    .andUserStatusGreaterThan(0);
            AccountDO accountDO = ListUtils.getOne(accountDOMapper.selectByExample(accountDoExample));
            if (!BeanUtils.isEmpty(accountDO)) {
                if (!BeanUtils.isEmpty(accountDO.getLockTime()) &&
                        accountDO.getLockTime().after(new Date())) {
                    return "";
                }
                return uuid;
            }
        }
        return "";
    }
}
