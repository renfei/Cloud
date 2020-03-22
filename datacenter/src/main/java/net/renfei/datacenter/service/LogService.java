package net.renfei.datacenter.service;

import net.renfei.api.datacenter.entity.LogDTO;
import net.renfei.datacenter.database.entity.LogDO;
import net.renfei.datacenter.database.persistences.LogDOMapper;
import net.renfei.sdk.utils.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author RenFei
 */
@Service
public class LogService {
    @Autowired
    private LogDOMapper logDOMapper;

    public int saveLog(LogDTO logDTO) {
        LogDO logDO = Builder.of(LogDO::new)
                .with(LogDO::setUuid, UUID.randomUUID().toString())
                .with(LogDO::setDatetime, logDTO.getDatetime())
                .with(LogDO::setLevel, logDTO.getLevel().getType())
                .with(LogDO::setInorout, logDTO.getInorout().getType())
                .with(LogDO::setRemoteIp, logDTO.getRemoteIp())
                .with(LogDO::setRemoteUser, logDTO.getRemoteUser())
                .with(LogDO::setRemoteAgent, logDTO.getRemoteAgent())
                .with(LogDO::setRequestUrl, logDTO.getRequestUrl())
                .with(LogDO::setRequestMethod, logDTO.getRequestMethod())
                .with(LogDO::setStatusCode, logDTO.getStatus().getCode())
                .with(LogDO::setLogValue, logDTO.getLogValue())
                .build();
        return logDOMapper.insertSelective(logDO);
    }
}
