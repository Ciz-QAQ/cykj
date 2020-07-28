package com.car.bean;

import java.util.List;

public class PageBean<T> {

    private int curPage;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalRecord;
    private int pageSize;
    private List<T> list;

    public PageBean() {
    }

    public PageBean(int curPage, int pageSize, int totalRecord){
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        totalPage = totalRecord%pageSize==0?totalRecord/pageSize:totalRecord/pageSize+1;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "curPage=" + curPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", totalRecord=" + totalRecord +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPrePage() {
        return curPage==1?1:curPage-1;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return curPage==totalPage?totalPage:curPage+1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
