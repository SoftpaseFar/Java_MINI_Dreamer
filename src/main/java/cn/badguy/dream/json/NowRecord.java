package cn.badguy.dream.json;

import java.util.ArrayList;

public class NowRecord {
    private boolean status;
    private String message;
    private ArrayList<ListOfNow> data;

    public NowRecord() {
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

    public ArrayList<ListOfNow> getData() {
        return data;
    }

    public void setData(ArrayList<ListOfNow> data) {
        this.data = data;
    }
}
