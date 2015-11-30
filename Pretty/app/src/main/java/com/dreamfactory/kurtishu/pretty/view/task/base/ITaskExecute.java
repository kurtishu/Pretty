package com.dreamfactory.kurtishu.pretty.view.task.base;

import android.os.Message;

/**
 * Created by kurtishu on 11/30/15.
 */
public interface ITaskExecute {

    void runBackground();

    void postSuccessToUI(Message msg);

    void postErrorToUI(String errorMsg);
}
