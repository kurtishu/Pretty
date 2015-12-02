package com.dreamfactory.kurtishu.pretty.api;


import com.dreamfactory.kurtishu.pretty.api.entity.ImageDetail;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.config.Config;
import com.dreamfactory.kurtishu.pretty.config.MockData;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kurtishu on 11/30/15.
 */
public class ImageService {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();


    public List<Galleryclass> getClasslfy() throws Exception {

        Request request = new Request.Builder().url(Config.URL.IMAGE_CLASSIFY).build();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);

        String responseJson = null;
        if (Config.ISUSINGMOCKDATA) {
            responseJson = MockData.getFileString("classfly");
        } else {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }
        Logger.json(responseJson);
        List<Galleryclass> classlfy = new ArrayList<Galleryclass>();

        JSONArray jsonArray = new JSONArray(responseJson);
        for (int i = 0 ; i < jsonArray.length() ; i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            Galleryclass galleryclass = gson.fromJson(object.toString(), Galleryclass.class);
            classlfy.add(galleryclass);
        }
        return classlfy;
    }

    public ImageList getList(SearchEntity searchEntity) throws IOException {
        String url = Config.URL.IMAGE_LIST;

        if (null != searchEntity) {
            if (-1 == searchEntity.id) {
                url += "?page=" + searchEntity.page + "&rows=" + searchEntity.rows;
            } else {
                url += "?id=" + searchEntity.id + "&page=" + searchEntity.page + "&rows=" + searchEntity.rows;
            }

        }

        Request request = new Request.Builder().url(url).build();
        String responseJson = null;
        if (Config.ISUSINGMOCKDATA) {
            responseJson = MockData.getFileString("imageList");
        } else {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }
        Logger.json(responseJson);
        ImageList imageList = gson.fromJson(responseJson, ImageList.class);

        return imageList;
    };


    public ImageList getNewImageList(SearchEntity searchEntity) throws IOException {
        String url = Config.URL.IMAGE_NEW;

        if (null != searchEntity) {
            url += "?id=" + searchEntity.id + "&classify=" + searchEntity.classify + "&rows=" + searchEntity.rows;
        }

        Request request = new Request.Builder().url(url).build();
        String responseJson = null;
        if (Config.ISUSINGMOCKDATA) {
            responseJson = MockData.getFileString("imageNew");
        } else {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }
        Logger.json(responseJson);
        ImageList imageList = gson.fromJson(responseJson, ImageList.class);

        return imageList;
    }

    public ImageDetail getImageDetail(int id) throws IOException {
        String url = Config.URL.IMAGE_SHOW + "?id=" + id;
        Request request = new Request.Builder().url(url).build();
        String responseJson = null;
        if (Config.ISUSINGMOCKDATA) {
            responseJson = MockData.getFileString("imageDetails");
        } else {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }
        Logger.json(responseJson);

        ImageDetail detail = gson.fromJson(responseJson, ImageDetail.class);
        return detail;
    }
}
