package com.dreamfactory.kurtishu.pretty.view.task;

import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.ImageService;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.orhanobut.logger.Logger;

/**
 * Created by kurtishu on 11/30/15.
 */
public class SearchImagesTask extends ExecutableThread {


    public Handler mHandler;
    public SearchEntity searchEntity;

    public SearchImagesTask(Handler mHandler, SearchEntity searchEntity) {
        this.mHandler = mHandler;
        this.searchEntity = searchEntity;
    }

    @Override
    public void runBackground() {
        try {
            ImageList list = new ImageService().getList(searchEntity);
            setPostMessage(ExecutableThread.EXECUTE_STATE_SUCCESS, list);
        } catch (Exception e) {
            setPostMessage(ExecutableThread.EXECUTE_STATE_FAILURE, e.getMessage());
        }
    }

    @Override
    public void postSuccessToUI(Message msg) {
        mHandler.sendMessage(msg);
    }

    @Override
    public void postErrorToUI(String message) {
        Logger.i("Error Message: %s", message);
    }
}
