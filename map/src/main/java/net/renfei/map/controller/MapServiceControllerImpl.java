package net.renfei.map.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.api.map.entity.MapVO;
import net.renfei.api.map.service.MapService;
import net.renfei.map.service.MapImplService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地图服务
 *
 * @author RenFei
 */
@RestController
@Api(tags = "地图空间服务接口")
public class MapServiceControllerImpl implements MapService {
    @Autowired
    private MapImplService mapImplService;

    /**
     * 根据经纬度和距离查询周边的点
     *
     * @return
     */
    @Override
    @ApiOperation("根据经纬度和距离查询周边的点")
    public APIResult getMapsByDistance(MapVO mapVO) {
        return APIResult.builder()
                .code(StateCode.OK)
                .data(mapImplService.getMapsByDistance(mapVO.getLatitude(), mapVO.getLongitude(), mapVO.getDistance(), mapVO.getSize()))
                .build();
    }
}
