package net.renfei.datacenter.database.persistences;

import java.util.List;
import net.renfei.datacenter.database.entity.TokenDO;
import net.renfei.datacenter.database.entity.TokenDOExample;
import org.apache.ibatis.annotations.Param;

public interface TokenDOMapper {
    long countByExample(TokenDOExample example);

    int deleteByExample(TokenDOExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(TokenDO record);

    int insertSelective(TokenDO record);

    List<TokenDO> selectByExample(TokenDOExample example);

    TokenDO selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") TokenDO record, @Param("example") TokenDOExample example);

    int updateByExample(@Param("record") TokenDO record, @Param("example") TokenDOExample example);

    int updateByPrimaryKeySelective(TokenDO record);

    int updateByPrimaryKey(TokenDO record);
}