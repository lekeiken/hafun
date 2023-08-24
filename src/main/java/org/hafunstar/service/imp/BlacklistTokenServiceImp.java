package org.hafunstar.service.imp;

import org.hafunstar.dao.BlacklistTokenDao;
import org.hafunstar.domain.BlacklistToken;
import org.hafunstar.service.BlacklistTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Service
public class BlacklistTokenServiceImp implements BlacklistTokenService {


    private BlacklistTokenDao blacklistTokenDao;
    @Autowired
    public void setBlacklistTokenDao(BlacklistTokenDao blacklistTokenDao) {
        this.blacklistTokenDao = blacklistTokenDao;
    }

    @Override
    public void saveBlacklistToken(BlacklistToken blacklistToken) {

        blacklistTokenDao.tokenListSave(blacklistToken);
    }

    @Override
    public boolean tokenIsExist(String token) {
        if(blacklistTokenDao.findTokenIsExist(token) != null){
            return true;
        }else {
            return false;
        }
    }

}
