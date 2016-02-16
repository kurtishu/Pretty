package com.dreamfactory.kurtishu.pretty.view.task.base;

import android.os.Message;

/**
 * Created by kurtishu on 11/30/15.
 */
public abstract  class ExecutableThread extends Thread implements ITaskExecute {

    private volatile boolean mIsCanceled = false;
    public static final int EXECUTE_STATE_SUCCESS = 1;
    public static final int EXECUTE_STATE_FAILURE = 0;

    public void cancel() {
        setCanceled(true);
    }

    public boolean isCanceled() {
        return mIsCanceled;
    }
    private Message mPostMessage = new Message();

    private void setCanceled(boolean isCanceled) {
        this.mIsCanceled = isCanceled;
    }

    protected void setPostMessage(int msgWhat, Object msgObj) {
        this.mPostMessage.what = msgWhat;
        this.mPostMessage.obj = msgObj;
    }

    private Message getmPostMessage() {
        return mPostMessage;
    }

    @Override
    public void run() {
        executeThread();
    }

    private void executeThread() {

        if (isCanceled()) {
            return;
        }

        runBackground();

        if (isCanceled()) {
            return;
        }

        if (mPostMessage.what == EXECUTE_STATE_FAILURE) {
            postErrorToUI((String) getmPostMessage().obj);
        } else {
            postSuccessToUI(getmPostMessage());
        }
    }
}
