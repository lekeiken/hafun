package org.hafunstar.service.imp;

import org.hafunstar.dao.HistoryDao;
import org.hafunstar.dao.HomePageDao;
import org.hafunstar.domain.History;
import org.hafunstar.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

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
public class HistoryServiceImp implements HistoryService {

    private HistoryDao  historyDao;
    @Autowired
    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    @Override
    public List<History> findHistoryAll() {
        return historyDao.findHisAll();
    }

    @Override
    public int findHisUrl(String s) {

        return historyDao.findHisUrl(s);
    }

    @Override
    public int findHisCount() {
        return historyDao.findHistoryCount();
    }
}
