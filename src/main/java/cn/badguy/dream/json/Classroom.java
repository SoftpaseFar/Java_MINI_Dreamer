package cn.badguy.dream.json;

import java.util.ArrayList;

public class Classroom {

    private boolean status;
    private String message;
    private ArrayList<Room> data;



    public Classroom() {
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

    public ArrayList<Room> getData() {
        return data;
    }

    public void setData(ArrayList<Room> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
