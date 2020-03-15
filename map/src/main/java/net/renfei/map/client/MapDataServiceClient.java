package net.renfei.map.client;

import net.renfei.api.map.service.MapDataService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "datacenter")
public interface MapDataServiceClient extends MapDataService {
}
