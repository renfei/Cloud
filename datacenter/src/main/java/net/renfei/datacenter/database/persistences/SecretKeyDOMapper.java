package net.renfei.datacenter.database.persistences;

import java.util.List;
import net.renfei.datacenter.database.entity.SecretKeyDO;
import net.renfei.datacenter.database.entity.SecretKeyDOExample;
import org.apache.ibatis.annotations.Param;

public interface SecretKeyDOMapper {
    long countByExample(SecretKeyDOExample example);

    int deleteByExample(SecretKeyDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(SecretKeyDO record);

    int insertSelective(SecretKeyDO record);

    List<SecretKeyDO> selectByExample(SecretKeyDOExample example);

    SecretKeyDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SecretKeyDO record, @Param("example") SecretKeyDOExample example);

    int updateByExample(@Param("record") SecretKeyDO record, @Param("example") SecretKeyDOExample example);

    int updateByPrimaryKeySelective(SecretKeyDO record);

    int updateByPrimaryKey(SecretKeyDO record);
}