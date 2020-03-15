package net.renfei.api.datacenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author RenFei
 */
@Data
public class TokenDTO {
    private String uuid;
    private String userId;
    private String token;
    private Date expiration;
    private Date issueTime;
}
