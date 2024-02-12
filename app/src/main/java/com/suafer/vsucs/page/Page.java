package com.suafer.vsucs.page;

import com.suafer.vsucs.enumpack.PageType;

public class Page {

    private final PageType pageType;
    private boolean status;

    public Page(PageType pageType, boolean status) {
        this.pageType = pageType;
        this.status = status;
    }

    public PageType getPageType() {return pageType;}
    public boolean getStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}
}
