package com.roadjava.req;

public class StudentRequest {
    private  int pagenow;
    private  int pagesize;
    private  int start;
    //查询词
    private   String searchKey;
    public int  getPagenow() {
        return pagenow;
    }

    public int getStart() {
        return 10*(pagenow-1);
    }

    public void setPagenow(int pagenow) {
        this.pagenow = pagenow;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
