package cn.badguy.dream.service;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.json.ListOfAll;
import cn.badguy.dream.json.ListOfLost;
import cn.badguy.dream.json.ListOfNow;
import cn.badguy.dream.json.Room;
import cn.badguy.dream.pojo.Classroom;
import cn.badguy.dream.pojo.Student;
import okhttp3.OkHttpClient;

import java.util.List;

public interface IQfnuService {
    ServerResponse<String> Login(Student student, OkHttpClient client);

    ServerResponse<String> getSchedule(OkHttpClient client);

    ServerResponse<String> Logout(OkHttpClient client);

    ServerResponse<String> feedback(OkHttpClient client, String username, String title, String describe);

    ServerResponse<String> getToken(String username, String password);

    ServerResponse<List<Room>> getEmptyClassroom(Classroom classroom, String token);

    ServerResponse<List<ListOfAll>> getAllRecord(String token);

    ServerResponse<List<ListOfNow>> getNowRecord(String token);

    ServerResponse<List<ListOfLost>> getLostRecord(String token);
}
