package net.renfei.datacenter.controller;

import net.renfei.api.datacenter.entity.LogDTO;
import net.renfei.api.datacenter.service.LogDataService;
import net.renfei.datacenter.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志数据服务
 *
 * @author RenFei
 */
@RestController
public class LogDataImpl implements LogDataService {
    @Autowired
    private LogService logService;

    @Override
    public int log(@RequestBody LogDTO logDTO) {
        return logService.saveLog(logDTO);
    }
}
