package com.dreamfactory.kurtishu.pretty.config;

import android.content.Context;

import com.dreamfactory.kurtishu.pretty.PrettyApp;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.model.Gallery;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.dreamfactory.kurtishu.pretty.utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurtishu on 12/1/15.
 */
public class MockData {

    public static ImageList getImageList() {
        ImageList list = new ImageList();
        list.total = 3;
        list.tngou = getGalleryList();
        return list;
    }

    public static List<Gallery> getGalleryList() {
        List<Gallery> tngou = new ArrayList<Gallery>();
        Gallery gallery1 = new Gallery();
        gallery1.id = 1;
        gallery1.img = "http://img2.imgtn.bdimg.com/it/u=1857736260,3610884144&fm=21&gp=0.jpg";
        gallery1.title = "This is a test gallery1 fafXxxdfdaf";
        gallery1.title = "gallery1";
        gallery1.size = 1;
        gallery1.galleryclass = 1;

        tngou.add(gallery1);

        Gallery gallery2 = new Gallery();
        gallery2.id = 2;
        gallery2.img = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/07/c0/20637218_1367902397192.jpg";
        gallery2.title = "This is a test gallery1 fafXxxdfdaf";
        gallery2.title = "gallery2";
        gallery2.size = 1;
        gallery2.galleryclass = 1;

        tngou.add(gallery2);

        Gallery gallery3 = new Gallery();
        gallery3.id = 3;
        gallery3.img = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1209/24/c0/14088988_1348453951051.jpg";
        gallery3.title = "This is a test gallery1 fafXxxdfdaf";
        gallery3.title = "gallery2";
        gallery3.size = 1;
        gallery3.galleryclass = 1;

        tngou.add(gallery3);
        tngou.add(gallery2);
        tngou.add(gallery1);

        return tngou;
    }

    public static String getFileString(String fileName) {
        InputStream inputStream = null;
        String json = null;
        try {
            Context context = PrettyApp.getContext();
            inputStream = context.getAssets().open(fileName);
            json = FileUtil.InputStreamTOString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           FileUtil.closeSteam(inputStream);
        }

        return json;
    }
}
