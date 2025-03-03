package models;

import java.util.List;

public class UsersResponseListResource {

    private int page;
    private List<UserDataListResource> data;
    private int per_page;
    private int total;
    private int total_pages;
    private Support support;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<UserDataListResource> getData() {
        return data;
    }

    public void setData(List<UserDataListResource> data) {
        this.data = data;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
