package cn.badguy.dream.controller;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.json.*;
import cn.badguy.dream.pojo.Classroom;
import cn.badguy.dream.pojo.Student;
import cn.badguy.dream.service.ILostItemsService;
import cn.badguy.dream.service.IQfnuService;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class QfnuController {

    private final static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            cookieStore.put(httpUrl.host(), list);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            List<Cookie> cookies = cookieStore.get(httpUrl.host());
            return cookies != null ? cookies : new ArrayList<>();
        }
    }).build();

    @Autowired
    private IQfnuService iQfnuService;

    @Autowired
    private ILostItemsService iLostItemsService;

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////badguy/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    /*
     *用户登录
     */
    @GetMapping("/qfnu/login")
    public ServerResponse<String> login(Student student) {
        return iQfnuService.Login(student, client);
    }


    /*
     *用户退出
     */
    @GetMapping("/qfnu/logout")
    public ServerResponse<String> logout() {
        return iQfnuService.Logout(client);
    }

    /*
     *获取课表(需要先登录)
     */
    @GetMapping("/qfnu/schedule")
    public ServerResponse<String> getSchedule() {
        return iQfnuService.getSchedule(client);
    }

    /*
     *用户反馈
     */
    @GetMapping("/qfnu/feedback")
    public ServerResponse<String> feedback(String username, String title, String describe) {
        return iQfnuService.feedback(client, username, title, describe);
    }


    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////klz///////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    /*
     *获取Token
     */
    @PostMapping("/other/qfnu/token")
    public ServerResponse<String> getToken(String username, String password) {
        return iQfnuService.getToken(username, password);
    }


    /*
     *获取空教室
     */
    @GetMapping("/other/qfnu/classroom")
    public ServerResponse<List<Room>> getEmptyClassroom(Classroom classroom, String token) {
        return iQfnuService.getEmptyClassroom(classroom, token);
    }

    /*
     *获取全部成绩
     */
    @GetMapping("/other/qfnu/all_record")
    public ServerResponse<List<ListOfAll>> getAllRecord(String token) {
        return iQfnuService.getAllRecord(token);
    }


    /*
     *获取本学期成绩
     */
    @GetMapping("/other/qfnu/current_record")
    public ServerResponse<List<ListOfNow>> getNowRecord(String token) {
        return iQfnuService.getNowRecord(token);
    }

    /*
     *获取不及格成绩
     */
    @GetMapping("/other/qfnu/fail_record")
    public ServerResponse<List<ListOfLost>> getLostRecord(String token) {
        return iQfnuService.getLostRecord(token);
    }

    /////////////////////////////////////////////////////////////////////
    ///////////////////////失物招(restful)领专区/////////////////////////
    /////////////////////////////////////////////////////////////////////

    /*
     *获取丢失物品列表(已解决、未解决)
     */
    @GetMapping("/qfnu/{type}/all/{solve}")
    public ServerResponse<List<ItemOfLost>> getLostItems(@PathVariable Integer type, @PathVariable Boolean solve) {
        return iLostItemsService.getLostItems(client, type, solve, false);
    }

    /*
     *获取丢失物品列表(获取全部)
     */
    @GetMapping("/qfnu/{type}/all")
    public ServerResponse<List<ItemOfLost>> getLostItemsAll(@PathVariable Integer type) {
        return iLostItemsService.getLostItemsAll(client, type);
    }


    /*
     *添加一条数据到丢书物品区
     */
    @PostMapping("/qfnu/{type}")
    public ServerResponse<String> insetLostItem(@PathVariable Integer type, ItemOfLost item) {
        return iLostItemsService.insetLostItem(client, type, item);
    }

    /*
     *丢失物品是否解决
     */
    @PutMapping("/qfnu/{type}")
    public ServerResponse<String> updateLostItem(@PathVariable Integer type, String obgId, Boolean solve) {
        return iLostItemsService.updateLostItem(client, type, obgId, solve);
    }

    /*
     *丢失物品删除
     */
    @DeleteMapping("/qfnu/{type}")
    public ServerResponse<String> deleteLostItem(@PathVariable Integer type, String obgId) {
        return iLostItemsService.deleteLostItem(client, type, obgId);
    }

    /*
     *获取某个学生发布的信息
     */
    @GetMapping("/qfnu/{type}/one")
    public ServerResponse<List<ItemOfLost>> getLostItemOne(@PathVariable Integer type, String username) {
        return iLostItemsService.getLostItemOne(client, type, username);
    }


}
