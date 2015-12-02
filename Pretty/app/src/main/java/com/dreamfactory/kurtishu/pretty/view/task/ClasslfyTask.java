package com.dreamfactory.kurtishu.pretty.view.task;

import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.ImageService;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by kurtishu on 11/30/15.
 */
public class ClasslfyTask extends ExecutableThread{

    private Handler mHandler;

    public ClasslfyTask(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void runBackground() {

        try {
            List<Galleryclass> galleryclasses = new ImageService().getClasslfy();

            setPostMessage(ExecutableThread.EXECUTE_STATE_SUCCESS, galleryclasses);
        } catch (Exception e) {
            Logger.e("Error message: " + e.toString());
            setPostMessage(ExecutableThread.EXECUTE_STATE_FAILURE, e.getMessage());
        }
    }

    @Override
    public void postSuccessToUI(Message msg) {
       mHandler.sendMessage(msg);
    }

    @Override
    public void postErrorToUI(String message) {
        mHandler.sendEmptyMessage(ExecutableThread.EXECUTE_STATE_FAILURE);
        Logger.i("Error Message: %s" , message);
    }
}
