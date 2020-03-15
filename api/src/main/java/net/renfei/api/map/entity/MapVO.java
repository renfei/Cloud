package net.renfei.api.map.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 地图服务查询对象
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "地图服务查询对象")
public class MapVO {
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;
    /**
     * 距离（米）
     */
    @ApiModelProperty(value = "距离（米）")
    private Integer distance;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer size;
}
