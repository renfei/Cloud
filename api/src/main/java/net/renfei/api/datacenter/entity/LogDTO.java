package net.renfei.api.datacenter.entity;

import lombok.Data;
import net.renfei.sdk.comm.StateCode;

import java.util.Date;

/**
 * 日志
 *
 * @author RenFei
 */
@Data
public class LogDTO {
    private String uuid;
    private Date datetime;
    private Level level;
    private Direction inorout;
    private String logValue;
    private String remoteIp;
    private String remoteUser;
    private String remoteAgent;
    private String requestUrl;
    private String requestMethod;
    private StateCode status;
}
