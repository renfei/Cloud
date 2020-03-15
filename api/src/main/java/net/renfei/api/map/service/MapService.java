package net.renfei.api.map.service;

import net.renfei.api.map.entity.MapVO;
import net.renfei.sdk.entity.APIResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 地图服务
 *
 * @author RenFei
 */
public interface MapService {
    /**
     * @param mapVO 查询对象
     * @return
     */
    @GetMapping("/map/distance")
    APIResult getMapsByDistance(MapVO mapVO);
}
