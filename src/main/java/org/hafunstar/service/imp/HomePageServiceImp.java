package org.hafunstar.service.imp;

import org.hafunstar.dao.CommentDao;
import org.hafunstar.dao.HistoryDao;
import org.hafunstar.dao.HomePageDao;
import org.hafunstar.dao.SonCommentDao;
import org.hafunstar.domain.Comment;
import org.hafunstar.domain.History;
import org.hafunstar.domain.HomePage;
import org.hafunstar.service.HomePageService;
import org.hafunstar.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class HomePageServiceImp implements HomePageService {


    private HomePageDao homePageDao;
    @Autowired
    public void setHomePageDao(HomePageDao homePageDao) {
        this.homePageDao = homePageDao;
    }
    private HistoryDao historyDao;
    @Autowired
    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    private CommentDao commentDao;
    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    private SonCommentDao sonCommentDao;
    @Autowired
    public void setSonCommentDao(SonCommentDao sonCommentDao) {
        this.sonCommentDao = sonCommentDao;
    }

    private History history;
    @Autowired
    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public HomePage findHomePageById(int id) {
        return homePageDao.findHomePageById(id);
    }

    @Override
    public HomePage findHomePageByNew(int code) {
        //1表示的是草稿，0表示的是编辑好的

        return homePageDao.findByStatusCode(code);
    }

    public int saveHomePageDraft(HomePage hp){
        return homePageDao.savePageInfo(hp);
    }

    @Override
    @Transactional
    public int saveHomePageInfo(HomePage hp) {
        try {

            int i = homePageDao.savePageInfo(hp);
            //获取一个6位数字字符串，如果数据库表里有重复的就再获取一个
            String s = RandomUtil.sixNum();
            while (historyDao.findHisUrl(s) > 0){
                s = RandomUtil.sixNum();
            }
            history.setHomePage(hp);
            history.setHisUrl(s);
            if(i > 0){
                i = historyDao.saveHis(history);
                return i;
            }else {
                return 0;
            }
        }catch (Exception e){
            return -1;
        }
    }
    @Override
    public int changeHomePage(HomePage hp){

        return homePageDao.changePageInfo(hp);
    }

    @Override
    public int deleteHomePageByInt(Integer i) {
        try {
            int res = homePageDao.deletePageByInt(i);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    @Transactional
    public int deleteHomePageById(Integer id) {

        int res = 1;
        //先删除和这个页面有关的评论再删除历史表，最后删除主页表
        List<Integer> comId = new ArrayList<>();
        List<Comment> comments = commentDao.findCidByPid(id);
        for(Comment c:comments){
            comId.add(c.getCommentId());
        }
        Integer [] idArr = comId.toArray(new Integer[0]);
        res = sonCommentDao.deleteSonCommentByFid(idArr);
        res = commentDao.deleteCommentById(idArr);
        res = historyDao.deleteHis(id);
        res = homePageDao.deletePageById(id);

        return res;

    }
}
