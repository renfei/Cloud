package net.renfei.account.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 账户服务配置
 *
 * @author RenFei
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "account")
public class AccountConfig {
    public String jwtKey;
    public String jwtIssuer;
}
