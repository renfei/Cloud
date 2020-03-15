package net.renfei.account.client;

import net.renfei.api.datacenter.service.AccountDataService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "datacenter")
public interface AccountDataServiceClient extends AccountDataService {
}
