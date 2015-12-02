package com.dreamfactory.kurtishu.pretty.event;

import java.util.Map;

/**
 * Created by kurtishu on 12/1/15.
 */
public class NavigatorEvent {

    private final Map<String, ?> params;


    public NavigatorEvent(Map<String, ?> params) {
      this.params = params;
    }

    public Map<String, ?> getParams() {
        return params;
    }
}
