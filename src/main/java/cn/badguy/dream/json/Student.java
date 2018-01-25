package cn.badguy.dream.json;

import java.util.ArrayList;

public class Student {
    private String request_id;
    private ArrayList<StuProperties> ret;
    private boolean success;

    public Student() {
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public ArrayList<StuProperties> getRets() {
        return ret;
    }

    public void setRets(ArrayList<StuProperties> rets) {
        this.ret = rets;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
