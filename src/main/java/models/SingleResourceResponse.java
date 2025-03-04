package models;

public class SingleResourceResponse {

    private UserDataListResource data;
    private Support support;

    public UserDataListResource getData() {
        return data;
    }

    public void setData(UserDataListResource data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
