package net.renfei.map.service;

import net.renfei.api.map.entity.MapDTO;
import net.renfei.map.client.MapDataServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地图服务
 *
 * @author RenFei
 */
@Service
public class MapImplService {
    @Autowired
    private MapDataServiceClient mapDataServiceClient;
    /**
     * 根据经纬度和距离查询周边的点
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param distance  距离（米）
     * @param size      获取多少个
     * @return
     */
    public List<MapDTO> getMapsByDistance(Double latitude, Double longitude, int distance, int size) {
        return mapDataServiceClient.getMapsByDistance(latitude, longitude, distance, size);
    }
}
