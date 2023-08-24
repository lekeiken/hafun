package org.hafunstar.dao;

import org.hafunstar.domain.BlacklistToken;
import org.springframework.stereotype.Repository;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Repository
public interface BlacklistTokenDao {

    /**
     * 保存一个token到黑名单
     */
    void tokenListSave(BlacklistToken blacklistToken);

    /**
     * 查询一个token是否存在
     * @param
     * @return
     */
     BlacklistToken findTokenIsExist(String token);
}