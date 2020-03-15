package net.renfei.datacenter.service;

import net.renfei.api.datacenter.entity.TokenDTO;
import net.renfei.datacenter.database.entity.TokenDO;
import net.renfei.datacenter.database.entity.TokenDOExample;
import net.renfei.datacenter.database.persistences.TokenDOMapper;
import net.renfei.sdk.utils.Builder;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author RenFei
 */
@Service
public class TokenService {
    @Autowired
    private TokenDOMapper tokenDOMapper;

    public int saveToken(TokenDTO tokenDTO){
        TokenDO tokenDO = new TokenDO();
        BeanUtils.copyProperties(tokenDTO,tokenDO);
        return tokenDOMapper.insertSelective(tokenDO);
    }

    public TokenDTO getToken(String token){
        TokenDOExample tokenDoExample = new TokenDOExample();
        tokenDoExample.createCriteria()
                .andTokenEqualTo(token)
                .andExpirationGreaterThanOrEqualTo(new Date());
        TokenDO tokenDO = ListUtils.getOne(tokenDOMapper.selectByExample(tokenDoExample));
        if (tokenDO != null) {
            //延长有效期
            extensionValidity(tokenDO.getUuid());
            TokenDTO tokenDTO=new TokenDTO();
            BeanUtils.copyProperties(tokenDO,tokenDTO);
            return tokenDTO;
        }
        return null;
    }

    /**
     * 延长token有效期30分钟
     *
     * @param tokenId
     */
    @Async
    public void extensionValidity(String tokenId) {
        TokenDO tokenDO = Builder.of(TokenDO::new)
                .with(TokenDO::setUuid, tokenId)
                .with(TokenDO::setExpiration, new Date(System.currentTimeMillis() + 1800000L))
                .build();
        tokenDOMapper.updateByPrimaryKeySelective(tokenDO);
    }
}
