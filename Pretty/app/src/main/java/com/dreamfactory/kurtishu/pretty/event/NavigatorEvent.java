package com.dreamfactory.kurtishu.pretty.event;

import java.util.Map;

/**
 * Created by kurtishu on 12/1/15.
 */
public class NavigatorEvent {

    private final Map<String, ?> params;
    private final boolean isFinish;

    public NavigatorEvent(Map<String, ?> params, boolean isFinish) {
      this.params = params;
        this.isFinish = isFinish;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public boolean isFinish() {
        return this.isFinish;
    }
}
