package net.renfei.api.datacenter.entity;

import lombok.Data;

/**
 * @author RenFei
 */
@Data
public class SecretKeyDTO {
    private String id;
    private String publicKey;
    private String privateKey;
}
