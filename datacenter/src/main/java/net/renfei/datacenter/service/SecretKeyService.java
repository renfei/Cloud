package net.renfei.datacenter.service;

import net.renfei.api.datacenter.entity.SecretKeyDTO;
import net.renfei.datacenter.database.entity.SecretKeyDO;
import net.renfei.datacenter.database.persistences.SecretKeyDOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author RenFei
 */
@Service
public class SecretKeyService {
    @Autowired
    private SecretKeyDOMapper secretKeyDOMapper;

    public int save(SecretKeyDTO secretKeyDTO) {
        SecretKeyDO secretKeyDO = new SecretKeyDO();
        BeanUtils.copyProperties(secretKeyDTO, secretKeyDO);
        return secretKeyDOMapper.insertSelective(secretKeyDO);
    }
}
