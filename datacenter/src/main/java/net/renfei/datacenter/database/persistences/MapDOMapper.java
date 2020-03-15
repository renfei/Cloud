package net.renfei.datacenter.database.persistences;

import java.util.List;

import net.renfei.datacenter.database.entity.MapDO;
import net.renfei.datacenter.database.entity.MapDOEx;
import net.renfei.datacenter.database.entity.MapDOExample;
import org.apache.ibatis.annotations.Param;

public interface MapDOMapper {
    long countByExample(MapDOExample example);

    int deleteByExample(MapDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(MapDO record);

    int insertSelective(MapDO record);

    List<MapDO> selectByExample(MapDOExample example);

    MapDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MapDO record, @Param("example") MapDOExample example);

    int updateByExample(@Param("record") MapDO record, @Param("example") MapDOExample example);

    int updateByPrimaryKeySelective(MapDO record);

    int updateByPrimaryKey(MapDO record);

    List<MapDOEx> selectMapsByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("distance") int distance);
}