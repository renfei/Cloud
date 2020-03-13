package net.renfei.account.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import net.renfei.account.config.AccountConfig;
import net.renfei.datacenter.database.entity.TokenDO;
import net.renfei.datacenter.database.entity.TokenDOExample;
import net.renfei.datacenter.database.persistences.TokenDOMapper;
import net.renfei.sdk.utils.Builder;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

/**
 * 令牌服务
 *
 * @author RenFei
 */
@Slf4j
@Service
public class JwtService {
    @Autowired
    private AccountConfig accountConfig;
    @Autowired
    private TokenDOMapper tokenDOMapper;

    /**
     * 生成一个Token
     *
     * @param subject 数据负载(UserID)
     * @return
     */
    public String build(String subject) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(accountConfig.getJwtKey()));
        String uuid = UUID.randomUUID().toString();
        Date now = new Date();
        String token = Jwts.builder()
                .setIssuer(accountConfig.getJwtIssuer())
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 604800000L))
                .setNotBefore(new Date(System.currentTimeMillis() - 1000L))
                .setIssuedAt(now)
                .setId(uuid)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        //保存到数据库
        TokenDO tokenDO = Builder.of(TokenDO::new)
                .with(TokenDO::setUuid, uuid)
                .with(TokenDO::setUserId, subject)
                .with(TokenDO::setToken, token)
                .with(TokenDO::setExpiration, new Date(System.currentTimeMillis() + 1800000L))
                .with(TokenDO::setIssueTime, now)
                .build();
        tokenDOMapper.insertSelective(tokenDO);
        return token;
    }

    /**
     * 读取Token
     *
     * @param token
     * @return
     */
    public String readToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(accountConfig.getJwtKey()));
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            if (accountConfig.getJwtIssuer().equals(claimsJws.getBody().getIssuer())) {
                //查验数据库
                TokenDOExample tokenDoExample = new TokenDOExample();
                tokenDoExample.createCriteria()
                        .andUuidEqualTo(claimsJws.getBody().getId())
                        .andUserIdEqualTo(claimsJws.getBody().getSubject())
                        .andTokenEqualTo(token)
                        .andExpirationGreaterThanOrEqualTo(new Date());
                TokenDO tokenDO = ListUtils.getOne(tokenDOMapper.selectByExample(tokenDoExample));
                if (tokenDO != null) {
                    //延长有效期
                    extensionValidity(tokenDO.getUuid());
                    return claimsJws.getBody().getSubject();
                }
            }
            return null;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }

    }

    /**
     * 生成一个安全秘钥
     *
     * @return
     */
    public String creatingSafeKeys() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Encoders.BASE64URL.encode(key.getEncoded());
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
