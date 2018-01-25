package cn.badguy.dream.json;
public class Room {
    private String campus;
    private String building;
    private String classroom;
    private String type;
    private String number;

    public Room() {
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Room{" +
                "campus='" + campus + '\'' +
                ", building='" + building + '\'' +
                ", classroom='" + classroom + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
