package net.renfei.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关配置
 *
 * @author RenFei
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "gateway")
public class GatewayConfig {
    private Boolean devMode;
    private List<String> ignore;
    private String tokenCode;
    private String timestampCode;
    private String signatureCode;
    private String nonceCode;
}
