package cn.badguy.dream.json;

import java.util.ArrayList;

public class LostRecord {
    private boolean status;
    private String message;
    private ArrayList<ListOfLost> data;

    public LostRecord() {
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

    public ArrayList<ListOfLost> getData() {
        return data;
    }

    public void setData(ArrayList<ListOfLost> data) {
        this.data = data;
    }
}
