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

package com.dreamfactory.kurtishu.pretty.view.activity;

import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.entity.ImageDetail;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.ImageViewerDelegate;
import com.dreamfactory.kurtishu.pretty.view.task.ImageDetailTask;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.orhanobut.logger.Logger;

public class ImageViewerActivity extends ActivityPresenter<ImageViewerDelegate> {

    @Override
    protected Class<ImageViewerDelegate> getDeletageClass() {
        return ImageViewerDelegate.class;
    }

    @Override
    protected void afterResume() {
        super.afterResume();

        int id = getIntent().getExtras().getInt("id");
        Logger.i("id = " + id);

        new ImageDetailTask(mHandler, id).start();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == ExecutableThread.EXECUTE_STATE_SUCCESS) {
                if (null !=  msg.obj) {
                    ImageDetail imageDetail = (ImageDetail) msg.obj;
                    getViewDelegate().updateData(imageDetail.list);
                }
            }
            super.handleMessage(msg);
        }
    };
}
