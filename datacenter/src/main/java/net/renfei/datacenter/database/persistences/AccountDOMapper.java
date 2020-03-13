package net.renfei.datacenter.database.persistences;

import java.util.List;
import net.renfei.datacenter.database.entity.AccountDO;
import net.renfei.datacenter.database.entity.AccountDOExample;
import org.apache.ibatis.annotations.Param;

public interface AccountDOMapper {
    long countByExample(AccountDOExample example);

    int deleteByExample(AccountDOExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    List<AccountDO> selectByExample(AccountDOExample example);

    AccountDO selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") AccountDO record, @Param("example") AccountDOExample example);

    int updateByExample(@Param("record") AccountDO record, @Param("example") AccountDOExample example);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);
}