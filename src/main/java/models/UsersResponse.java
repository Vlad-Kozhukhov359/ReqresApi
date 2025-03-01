package models;


import java.awt.*;
import java.util.List;

public class UsersResponse {

    private int page;
    private List <UserData> data;
    private int per_page;
    private int total;
    private int total_pages;
    private Support support;

    // Геттеры и сеттеры
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
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

    public List <UserData> getData() {
        return data;
    }

    public void setData(List <UserData> data) {
        this.data = data;
    }


}
