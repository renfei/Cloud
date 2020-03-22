package net.renfei.api.datacenter.service;

import net.renfei.api.datacenter.entity.LogDTO;
import org.springframework.web.bind.annotation.PostMapping;

public interface LogDataService {
    @PostMapping("/log")
    int log(LogDTO logDTO);
}
