package org.hafunstar.service;

import org.hafunstar.domain.History;

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

public interface HistoryService {

    List<History> findHistoryAll();

    int findHisUrl(String s);

    int findHisCount();
}
