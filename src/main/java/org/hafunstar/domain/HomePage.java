package org.hafunstar.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:主页信息表
 */
@Component
public class HomePage implements Serializable {
    private Integer pageId;
    private Integer pageIsNew;
    private String pageImg;
    private String pagePlatform;
    private String pageGameName;
    private String pageGameType;
    private String pageProducer;
    private Date pageLaunchDate;
    private String pageGameIntro;
    private String pageSystem;
    private String pageCpu;
    private String pageMemory;
    private String pageDisk;
    private String pageGraphics;
    private String pageEvaluation;
    private Integer pageIndex;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageIsNew() {
        return pageIsNew;
    }

    public void setPageIsNew(Integer pageIsNew) {
        this.pageIsNew = pageIsNew;
    }

    public String getPageImg() {
        return pageImg;
    }

    public void setPageImg(String pageImg) {
        this.pageImg = pageImg;
    }

    public String getPagePlatform() {
        return pagePlatform;
    }

    public void setPagePlatform(String pagePlatform) {
        this.pagePlatform = pagePlatform;
    }

    public String getPageGameName() {
        return pageGameName;
    }

    public void setPageGameName(String pageGameName) {
        this.pageGameName = pageGameName;
    }

    public String getPageGameType() {
        return pageGameType;
    }

    public void setPageGameType(String pageGameType) {
        this.pageGameType = pageGameType;
    }

    public String getPageProducer() {
        return pageProducer;
    }

    public void setPageProducer(String pageProducer) {
        this.pageProducer = pageProducer;
    }

    public void setPageLaunchDate(Date pageLaunchDate) {

        this.pageLaunchDate = pageLaunchDate;
    }
    public String getPageLaunchDate() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(pageLaunchDate);

    }

    public String getPageGameIntro() {
        return pageGameIntro;
    }

    public void setPageGameIntro(String pageGameIntro) {
        this.pageGameIntro = pageGameIntro;
    }

    public String getPageSystem() {
        return pageSystem;
    }

    public void setPageSystem(String pageSystem) {
        this.pageSystem = pageSystem;
    }

    public String getPageCpu() {
        return pageCpu;
    }

    public void setPageCpu(String pageCpu) {
        this.pageCpu = pageCpu;
    }

    public String getPageMemory() {
        return pageMemory;
    }

    public void setPageMemory(String pageMemory) {
        this.pageMemory = pageMemory;
    }

    public String getPageDisk() {
        return pageDisk;
    }

    public void setPageDisk(String pageDisk) {
        this.pageDisk = pageDisk;
    }

    public String getPageGraphics() {
        return pageGraphics;
    }

    public void setPageGraphics(String pageGraphics) {
        this.pageGraphics = pageGraphics;
    }

    public String getPageEvaluation() {
        return pageEvaluation;
    }

    public void setPageEvaluation(String pageEvaluation) {
        this.pageEvaluation = pageEvaluation;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "HomePage{" +
                "pageId=" + pageId +
                ", pageIsNew=" + pageIsNew +
                ", pageImg='" + pageImg + '\'' +
                ", pagePlatform='" + pagePlatform + '\'' +
                ", pageGameName='" + pageGameName + '\'' +
                ", pageGameType='" + pageGameType + '\'' +
                ", pageProducer='" + pageProducer + '\'' +
                ", pageLaunchDate=" + pageLaunchDate +
                ", pageGameIntro='" + pageGameIntro + '\'' +
                ", pageSystem='" + pageSystem + '\'' +
                ", pageCpu='" + pageCpu + '\'' +
                ", pageMemory='" + pageMemory + '\'' +
                ", pageDisk='" + pageDisk + '\'' +
                ", pageGraphics='" + pageGraphics + '\'' +
                ", pageEvaluation='" + pageEvaluation + '\'' +
                ", pageIndex=" + pageIndex +
                '}';
    }
}
