package net.renfei.datacenter.controller;

import com.github.pagehelper.PageHelper;
import net.renfei.api.map.entity.MapDTO;
import net.renfei.api.map.service.MapDataService;
import net.renfei.datacenter.database.entity.MapDOEx;
import net.renfei.datacenter.database.persistences.MapDOMapper;
import net.renfei.sdk.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图数据服务
 *
 * @author RenFei
 */
@RestController
public class MapDataImpl implements MapDataService {
    @Autowired
    private MapDOMapper mapDOMapper;

    @Override
    public List<MapDTO> getMapsByDistance(double latitude, double longitude, int distance, int size) {
//        PageHelper.startPage(1, size);
        List<MapDOEx> mapDOExes = mapDOMapper.selectMapsByDistance(latitude, longitude, distance);
        List<MapDTO> mapDTOS = new ArrayList<>();
        if (!BeanUtils.isEmpty(mapDOExes)) {
            mapDOExes.forEach(x -> {
                MapDTO mapDTO = new MapDTO();
                org.springframework.beans.BeanUtils.copyProperties(x, mapDTO);
                mapDTOS.add(mapDTO);
            });
        }
        return mapDTOS;
    }
}
