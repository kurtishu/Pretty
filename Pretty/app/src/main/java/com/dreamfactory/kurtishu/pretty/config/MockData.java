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
