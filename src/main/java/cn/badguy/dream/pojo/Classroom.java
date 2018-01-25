package cn.badguy.dream.pojo;

public class Classroom {
    private String building;// 教学楼
    private String campus;// 校区 1 曲阜 2 日照
    private String week;// 周次
    private String time;// 星期
    private String session;// 节次

    public Classroom() {
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
