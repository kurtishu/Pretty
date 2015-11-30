package com.dreamfactory.kurtishu.pretty.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kurtishu on 11/30/15.
 */
public interface IDelegate {

    void create(LayoutInflater inflater, ViewGroup v, Bundle b);

    View getRootView();
}
