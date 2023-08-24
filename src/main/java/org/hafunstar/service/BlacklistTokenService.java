package org.hafunstar.service;

import org.hafunstar.domain.BlacklistToken;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */

public interface BlacklistTokenService {

    /**
     *保存一个token
     */
    public void saveBlacklistToken(BlacklistToken blacklistToken);

    /**
     * 查询一个token是否存在
     */
    public boolean tokenIsExist(String token);
}
