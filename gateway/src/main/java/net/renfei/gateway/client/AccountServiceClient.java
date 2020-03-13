package net.renfei.gateway.client;

import net.renfei.api.account.service.AccountIdService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "account")
public interface AccountServiceClient extends AccountIdService {
}
