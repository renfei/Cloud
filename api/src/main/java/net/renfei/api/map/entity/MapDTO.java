package net.renfei.api.map.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 地图传输对象
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "地图")
public class MapDTO {
    /**
     * 地图上点的编号
     */
    @ApiModelProperty(value = "地图上点的编号")
    private String id;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private Integer distance;
}
