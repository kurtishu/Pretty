/*
 *
 *  * Copyright 2016 Kurtis <kurtis_hu@hotmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.dreamfactory.kurtishu.pretty.view.task;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.config.Config;
import com.dreamfactory.kurtishu.pretty.utils.FileUtil;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author：kurtishu on 2/16/16
 * Eevery one should have a dream, what if one day it comes true!
 */
public class DownloadImagTask extends ExecutableThread {

    private Handler mHandler;
    private String imgUrl;

    public static final int EXECUTE_STATE_DOWNLOAD_SUCCESS = 100;

    public DownloadImagTask(Handler handler, String imgUrl) {
        this.mHandler = handler;
        this.imgUrl = imgUrl;
    }

    @Override
    public void runBackground() {
        downloadImg(imgUrl);
    }

    @Override
    public void postSuccessToUI(Message msg) {
        mHandler.sendMessage(msg);
    }

    @Override
    public void postErrorToUI(String errorMsg) {
        mHandler.sendEmptyMessage(ExecutableThread.EXECUTE_STATE_FAILURE);
        Logger.i("Error Message: %s" , errorMsg);
    }

    private void downloadImg(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (null != response && response.isSuccessful()) {
                String filePath = storeImgToSDCard(response.body().byteStream());
                setPostMessage(EXECUTE_STATE_DOWNLOAD_SUCCESS, filePath);
            } else {
                setPostMessage(ExecutableThread.EXECUTE_STATE_FAILURE, "Internet error!");
            }
        } catch (IOException e) {
            Logger.e("Error message: " + e.toString());
            setPostMessage(ExecutableThread.EXECUTE_STATE_FAILURE, e.getMessage());
        }
    }

    private String storeImgToSDCard(InputStream inputStream) {
        String sdPath = FileUtil.getSDPath();
        File dirPath = new File(sdPath + Config.FILEDIR);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }

        String filePath = dirPath.getAbsolutePath() + File.separator + getCurrentTime() + ".jpg";
        FileUtil.writeToFile(inputStream, filePath);
        return filePath;
    }

    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
       return format.format(date);
    }
}
