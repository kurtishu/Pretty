package com.dreamfactory.kurtishu.pretty.view.delegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamfactory.kurtishu.pretty.view.base.IDelegate;

/**
 * Created by kurtishu on 11/30/15.
 */
public abstract class BaseAppDelegate implements IDelegate {

    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    public abstract int getRootLayoutId();

    protected void initViews(Context context, Intent mIntent){};

    @Override
    public void initViewControllers(Context context, Intent mIntent) {
        initViews(context, mIntent);
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle b) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }
}
