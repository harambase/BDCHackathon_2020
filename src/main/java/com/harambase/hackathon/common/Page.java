package com.harambase.hackathon.common;

public class Page {

    private int currentPage;

    private long totalRows;

    private int pageSize;

    public Page() {
        this.currentPage = -1;
        this.totalRows = -1L;
        this.pageSize = 20;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRows() {
        return this.totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrentIndex() {
        return pageSize * (currentPage - 1);
    }
}
