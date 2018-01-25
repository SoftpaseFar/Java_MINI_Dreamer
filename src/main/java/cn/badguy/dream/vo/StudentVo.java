package cn.badguy.dream.vo;

public class StudentVo {
    private String name;
    private String id;
    private String university;
    private String college;
    private String profession;
    private boolean StuCertify;
    private String phoneNum;

    public StudentVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isStuCertify() {
        return StuCertify;
    }

    public void setStuCertify(boolean stuCertify) {
        StuCertify = stuCertify;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
