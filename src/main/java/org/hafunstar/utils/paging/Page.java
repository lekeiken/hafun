package org.hafunstar.utils.paging;

import java.io.Serializable;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:分页类
 */

public class Page implements Serializable {
    private int pageNow = 1;//当前页码
    private int pageSize = 10;//每页显示多少条 默认 ：10
    private int totalCount;//总记录条数
    private int totalPageCount;//总页数
    private int beginIndex;//取数据的开始位置
    private Boolean hasPrePage;//是否有上一页
    private Boolean hasNextPage;//是否有下一页
    private int pageCount;//上一页最后一行数据编号，加总共已迭代的次数，得到本行数据编号

    public Page(int totalCount) {
        this.totalCount = totalCount;
        setTotalPageCount();
        setHasPrePage();
        setHasNextPage();
        setBeginIndex();
    }

    public Page(int totalCount, int pageNow) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
        setTotalPageCount();
        setHasPrePage();
        setHasNextPage();
        setBeginIndex();
    }
    public Page(int totalCount, int pageNow,int pageSize) {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        setTotalPageCount();
        setHasPrePage();
        setHasNextPage();
        setBeginIndex();
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize < 0 && pageSize > 30){
            this.pageSize = 10;
        }else {
            this.pageSize = pageSize;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPageCount() {

        if(totalCount % pageSize == 0){
            this.totalPageCount = getTotalCount() / getPageSize();
        }else {
            this.totalPageCount = getTotalCount() / getPageSize() + 1;
        }
    }

    public int getTotalPageCount(){
        return totalPageCount;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex() {
        this.beginIndex = (pageNow - 1) * pageSize;
    }

    public Boolean getHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage() {
        this.hasPrePage = (pageNow > 1) ? true : false;
    }

    public Boolean getHasNextPage() {

        return hasNextPage;
    }

    public void setHasNextPage() {
        this.hasNextPage = (pageNow < getTotalPageCount() && pageNow > 0) ? true : false;
    }

    public int getPageCount() {
        return (pageNow-1)*pageSize;
    }
}
