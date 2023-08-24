package org.hafunstar.dao;

import org.hafunstar.domain.History;
import org.springframework.stereotype.Repository;

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
@Repository
public interface HistoryDao {
    /**
     * 根据url编号查询主页id
     * @param num
     * @return
     */
    Integer findPidByUrl(int num);

    /**
     * 查询所有
     * @return
     */
    List<History> findHisAll();

    /**
     * 查询新生成的h_url是否重复
     * @param num
     * @return
     */
    int findHisUrl(String num);

    /**
     * 保存一个历史推荐
     * @param history
     */
    int saveHis(History history);

    /**
     * 删除一个
     * @param id
     * @return
     */
    int deleteHis(Integer id);

    /**
     * 查询总数
     */
    int findHistoryCount();

}
