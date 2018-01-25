package cn.badguy.dream.service;

import cn.badguy.dream.common.ServerResponse;
import cn.badguy.dream.json.ItemOfLost;
import okhttp3.OkHttpClient;

import java.util.List;

public interface ILostItemsService {
    ServerResponse<List<ItemOfLost>> getLostItems(OkHttpClient client, Integer type, boolean solve, boolean isDel);

    ServerResponse<List<ItemOfLost>> getLostItemsAll(OkHttpClient client, Integer type);

    ServerResponse<String> insetLostItem(OkHttpClient client, Integer type, ItemOfLost item);

    ServerResponse<String> updateLostItem(OkHttpClient client, Integer type, String objId, Boolean solve);

    ServerResponse<String> deleteLostItem(OkHttpClient client, Integer type, String obgId);

    ServerResponse<List<ItemOfLost>> getLostItemOne(OkHttpClient client, Integer type,String username);

}
