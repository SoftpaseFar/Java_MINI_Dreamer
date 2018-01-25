package cn.badguy.dream.service.Impl;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.exception.ParamException;
import cn.badguy.dream.json.ItemOfLost;
import cn.badguy.dream.json.LostItems;
import cn.badguy.dream.service.ILostItemsService;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service("iLostItemsService")
public class LostItemsServiceImpl implements ILostItemsService {

    public ServerResponse<List<ItemOfLost>> getLostItemOne(OkHttpClient client, Integer type, String username) {

        String table = getTable(type);
        String url = "https://api.bmob.cn/1/classes/" + table + "?where=%7B%22id%22:%22" + username + "%22,%22isDel%22:false%7D";
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                LostItems lostItems = gson.fromJson(response.body().string(), LostItems.class);
                List<ItemOfLost> ItemOfLosts = new ArrayList<>();
                for (ItemOfLost ItemOfLost : lostItems.getResults()) {
                    ItemOfLosts.add(ItemOfLost);
                }
                System.out.println();
                return ServerResponse.createBySuccess(ItemOfLosts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<String> deleteLostItem(OkHttpClient client, Integer type, String obgId) {
        String json = "{\"isDel\":true}";
        String table = getTable(type);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        String url = "https://api.bmob.cn/1/classes/" + table + "/" + obgId;

        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .put(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return ServerResponse.createBySuccessMessage(response.message());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<String> updateLostItem(OkHttpClient client, Integer type, String objId, Boolean solve) {

        String json = "{\"solve\":" + solve + "}";
        String table = getTable(type);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        String url = "https://api.bmob.cn/1/classes/" + table + "/" + objId;

        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .put(requestBody)
                .build();


        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return ServerResponse.createByErrorMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<String> insetLostItem(OkHttpClient client, Integer type, ItemOfLost item) {

        String table = getTable(type);
        Gson gson = new Gson();
        String json = gson.toJson(item);
        String url = "https://api.bmob.cn/1/classes/" + table;
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return ServerResponse.createBySuccessMessage("Insert success.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<List<ItemOfLost>> getLostItems(OkHttpClient client, Integer type, boolean solve, boolean isDel) {
        String table = getTable(type);
        String url = "https://api.bmob.cn/1/classes/" + table +
                "?where=%7B%22solve%22:" + solve + ",%22isDel%22:" + isDel + "%7D";
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                LostItems lostItems = gson.fromJson(response.body().string(), LostItems.class);
                List<ItemOfLost> ItemOfLosts = new ArrayList<>();
                for (ItemOfLost ItemOfLost : lostItems.getResults()) {
                    ItemOfLosts.add(ItemOfLost);
                }
                return ServerResponse.createBySuccess(ItemOfLosts);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    public ServerResponse<List<ItemOfLost>> getLostItemsAll(OkHttpClient client, Integer type) {

        String table = getTable(type);
        String url = "https://api.bmob.cn/1/classes/" + table + "?where=%7B%22isDel%22:false%7D";
        Request request = new Request
                .Builder()
                .addHeader("X-Bmob-Application-Id", "df35531048bf2ad0d3c3520d0ca45e2a")
                .addHeader("X-Bmob-REST-API-Key", "d1429634e69f82c7cdc54c9f7b25499e")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                LostItems lostItems = gson.fromJson(response.body().string(), LostItems.class);
                List<ItemOfLost> ItemOfLosts = new ArrayList<>();
                System.out.println("");
                for (ItemOfLost ItemOfLost : lostItems.getResults()) {
                    ItemOfLosts.add(ItemOfLost);
                }
                return ServerResponse.createBySuccess(ItemOfLosts);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ParamException(10001, "请求失败");
    }


    private String getTable(Integer type) {
        String table = "";
        if (type == 0) {
            table = "Lostitems";
        } else if (type == 1) {
            table = "Founditems";
        } else {
            throw new ParamException(10001, "类型错误");
        }
        return table;
    }
}
