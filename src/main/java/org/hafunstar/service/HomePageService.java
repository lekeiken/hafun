package org.hafunstar.service;

import org.hafunstar.domain.HomePage;

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

public interface HomePageService {

    HomePage findHomePageById(int id);

    HomePage findHomePageByNew(int code);

    int changeHomePage(HomePage hp);

    int saveHomePageDraft(HomePage hp);

    int saveHomePageInfo(HomePage hp);

    int deleteHomePageByInt(Integer i);

    int deleteHomePageById(Integer id);
}
