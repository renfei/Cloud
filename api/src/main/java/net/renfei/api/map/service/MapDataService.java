package net.renfei.api.map.service;

import net.renfei.api.map.entity.MapDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 地图数据服务
 *
 * @author RenFei
 */
public interface MapDataService {
    @GetMapping("/mapdata/distance")
    List<MapDTO> getMapsByDistance(@RequestParam(value = "latitude") double latitude,
                                   @RequestParam(value = "longitude") double longitude,
                                   @RequestParam(value = "distance") int distance,
                                   @RequestParam(value = "size") int size);
}
