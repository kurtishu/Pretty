package com.dreamfactory.kurtishu.pretty.api;


import com.dreamfactory.kurtishu.pretty.api.entity.ImageDetail;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.config.Config;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurtishu on 11/30/15.
 */
public class ImageService {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();


    public List<Galleryclass> getClasslfy() throws IOException {

        Request request = new Request.Builder().url(Config.URL.IMAGE_CLASSIFY).build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        Logger.json(responseJson);
        List<Galleryclass> classlfy = new ArrayList<Galleryclass>();
        classlfy = gson.fromJson(responseJson, classlfy.getClass());
        return classlfy;
    }

    public ImageList getList(SearchEntity searchEntity) throws IOException {
        String url = Config.URL.IMAGE_LIST;

        if (null != searchEntity) {
           url += "?id=" + searchEntity.id + "&page=" + searchEntity.page + "&rows=" + searchEntity.rows;
        }

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
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
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        Logger.json(responseJson);
        ImageList imageList = gson.fromJson(responseJson, ImageList.class);

        return imageList;
    }

    public ImageDetail getImageDetail(int id) throws IOException {
        String url = Config.URL.IMAGE_SHOW + "?id=" + id;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        Logger.json(responseJson);

        ImageDetail detail = gson.fromJson(responseJson, ImageDetail.class);
        return detail;
    }
}
