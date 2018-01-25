package cn.badguy.dream.json;

import java.util.ArrayList;

public class AllRecord {

    private boolean status;
    private String message;
    private ArrayList<ListOfAll> data;

    public AllRecord() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ListOfAll> getData() {
        return data;
    }

    public void setData(ArrayList<ListOfAll> data) {
        this.data = data;
    }
}
